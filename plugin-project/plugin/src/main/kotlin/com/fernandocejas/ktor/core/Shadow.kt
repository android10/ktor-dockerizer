package com.fernandocejas.ktor.core

import com.fernandocejas.ktor.DockerizerExtension
import com.fernandocejas.ktor.DockerizerJarTask
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.Project

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

            tasks.register(DockerizerJarTask.TASK_NAME, DockerizerJarTask::class.java) {
                it.extension.set(extension)
                it.dependsOn(TASK_SHADOW_JAR)
            }
        }
    }

    companion object {
        const val PLUGIN_SHADOW_JAR = "com.github.johnrengelman.shadow"
        const val TASK_SHADOW_JAR = "shadowJar"
    }
}
