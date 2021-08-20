package com.fernandocejas.ktor

import com.github.jengelman.gradle.plugins.shadow.tasks.*
import org.gradle.api.*


abstract class DockerizerPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val extension = project.extensions.create(PLUGIN_EXTENSION, DockerizerExtension::class.java)

        setupShadowJar(project, extension)
        setupDocker(project, extension)
    }

    /**
     * Sets up the generate shadow jar task in order to run Ktor through a fat jar.
     *
     * It relies on the gradle plugin [EXTERNAL_PLUGIN_SHADOW_JAR].
     *
     * @link: https://imperceptiblethoughts.com/shadow/getting-started/
     */
    private fun setupShadowJar(project: Project, pluginExtension: DockerizerExtension) {
        with(project) {
            plugins.apply(EXTERNAL_PLUGIN_SHADOW_JAR)

            project.afterEvaluate {
                tasks.withType(ShadowJar::class.java).configureEach {
                    it.archiveFileName.set("ktor-fernando-fat.jar")

                    it.exclude("META-INF/INDEX.LIST")
                    it.exclude("META-INF/*.SF")
                    it.exclude("META-INF/*.DSA")
                    it.exclude("META-INF/*.RSA")
                }

                tasks.register(DockerizerJarTask.TASK_NAME, DockerizerJarTask::class.java) {
                    it.extension.set(pluginExtension)
                }
            }

        }
    }

    /**
     * Sets up the docker task in order to run Ktor inside a docker container.
     */
    private fun setupDocker(project: Project, pluginExtension: DockerizerExtension) =
        project.tasks.register(DockerizerDockerTask.TASK_NAME, DockerizerDockerTask::class.java) {
            it.extension.set(pluginExtension)
        }

    companion object {
        const val EXTERNAL_PLUGIN_SHADOW_JAR = "com.github.johnrengelman.shadow"
        const val PLUGIN_EXTENSION = "dockerizer"
        const val TASK_GROUP = "Ktor Dockerizer"
    }
}
