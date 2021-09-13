package com.fernandocejas.ktor.core.jar

import com.fernandocejas.ktor.DockerizerExtension
import com.fernandocejas.ktor.DockerizerPlugin
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

/**
 * Class to handle/manage Ktor Fat Jar creation.
 * It relies on the 3rd party plugin from John Rengelman
 * called Shadow.
 *
 * @see: https://imperceptiblethoughts.com/shadow/getting-started/
 */
class Shadow(private val project: Project, private val extension: DockerizerExtension) {

    val pluginExcludes = setOf(
        "META-INF/INDEX.LIST",
        "META-INF/*.SF",
        "META-INF/*.DSA",
        "META-INF/*.RSA"
    )

    /**
     * Sets up the shadow gradle plugin to be able to generate a fat jar in
     * order to run Ktor through a jar with all the runtime dependencies.
     */
    fun applyPlugin() {
        with(project) {
            plugins.apply(PLUGIN_SHADOW_JAR)

            tasks.withType(ShadowJar::class.java).configureEach {
                it.apply {
                    archiveFileName.set(this@Shadow.extension.jarFilename.get().trim())
                    exclude(pluginExcludes)
                }
            }

            tasks.register(GENERATE_JAR_TASK_NAME, DockerizerJarTask::class.java) {
                it.extension.set(extension)
                it.dependsOn(TASK_SHADOW_JAR)
            }
        }
    }

    companion object {
        // Shadow Plugin
        const val PLUGIN_SHADOW_JAR = "com.github.johnrengelman.shadow"
        const val TASK_SHADOW_JAR = "shadowJar"

        // Dockerizer
        const val GENERATE_JAR_TASK_NAME = "generateJar"
        private const val GENERATE_JAR_TASK_DESCRIPTION = "Generates a Fat Jar for Ktor."

    }

    /**
     * This task is a wrapper over [Shadow] in order to decorate
     * and customize it in order to prepare a jar file to be executed
     * inside a Docker Container.
     */
    abstract class DockerizerJarTask : DefaultTask() {

        init {
            description = GENERATE_JAR_TASK_DESCRIPTION
            group = DockerizerPlugin.TASK_GROUP
        }

        @get:Input
        abstract val extension: Property<DockerizerExtension>

        @TaskAction
        fun generateJar() {
            val generatedJarFilename = extension.get().jarFilename.get().trim()
            logger.lifecycle("Fat jar '$generatedJarFilename' created in ${project.buildDir}/${generatedJarFilename}")
        }
    }
}
