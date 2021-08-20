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
     * It relies on the gradle plugin [EXTERNAL_PLUGIN_SHADOW_JAR] and task
     * [EXTERNAL_TASK_SHADOW_JAR].
     *
     * @see: https://imperceptiblethoughts.com/shadow/getting-started/
     */
    private fun setupShadowJar(project: Project, pluginExtension: DockerizerExtension) {
        with(project) {
            afterEvaluate {
                plugins.apply(EXTERNAL_PLUGIN_SHADOW_JAR)

                tasks.withType(ShadowJar::class.java).configureEach {
                    it.archiveFileName.set(pluginExtension.jarFilename.get().trim())

                    it.exclude("META-INF/INDEX.LIST")
                    it.exclude("META-INF/*.SF")
                    it.exclude("META-INF/*.DSA")
                    it.exclude("META-INF/*.RSA")
                }

                tasks.register(DockerizerJarTask.TASK_NAME, DockerizerJarTask::class.java) {
                    it.extension.set(pluginExtension)
                    it.dependsOn(EXTERNAL_TASK_SHADOW_JAR)
                }
            }
        }
    }

    /**
     * Sets up the docker task in order to run Ktor inside a docker container.
     */
    private fun setupDocker(project: Project, pluginExtension: DockerizerExtension) {
        with(project) {
            afterEvaluate {
                tasks.register(DockerizerDockerTask.TASK_NAME, DockerizerDockerTask::class.java) {
                    it.extension.set(pluginExtension)
                    it.dependsOn(DockerizerJarTask.TASK_NAME)
                }
            }
        }
    }

    companion object {
        // 3rd Party Gradle Plugin
        const val EXTERNAL_PLUGIN_SHADOW_JAR = "com.github.johnrengelman.shadow"
        const val EXTERNAL_TASK_SHADOW_JAR = "shadowJar"

        // Internal Configuration
        const val PLUGIN_EXTENSION = "dockerizer"
        const val TASK_GROUP = "Ktor Dockerizer"
    }
}
