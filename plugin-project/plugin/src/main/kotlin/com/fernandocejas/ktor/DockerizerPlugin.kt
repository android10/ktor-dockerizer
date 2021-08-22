package com.fernandocejas.ktor

import com.fernandocejas.ktor.core.Shadow
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Exec

abstract class DockerizerPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val extension = project.extensions.create(PLUGIN_EXTENSION, DockerizerExtension::class.java)
        val shadow = Shadow(project, extension)
        val docker = com.fernandocejas.ktor.core.Docker(project, extension)

        project.afterEvaluate {
            shadow.setupPlugin()
            docker.setupTasks()
        }
        setupShadowPlugin(project, extension)
        setupDockerTasks(project, extension)
    }

    /**
     * Sets up the shadow gradle plugin to be able to generate a fat jar in order to run Ktor
     * through a fat jar. It relies on the gradle plugin [EXTERNAL_PLUGIN_SHADOW_JAR] and task
     * [EXTERNAL_TASK_SHADOW_JAR].
     *
     * @see: https://imperceptiblethoughts.com/shadow/getting-started/
     */
    private fun setupShadowPlugin(project: Project, pluginExtension: DockerizerExtension) {
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
    private fun setupDockerTasks(project: Project, pluginExtension: DockerizerExtension) {
        with(project) {
            afterEvaluate {
                tasks.register(DockerizerDockerTask.TASK_NAME, DockerizerDockerTask::class.java) {
                    it.extension.set(pluginExtension)
                    it.dependsOn(DockerizerJarTask.TASK_NAME)
                }

                tasks.register("dockerBuildImage", Exec::class.java) {
//                    group = Docker.GROUP
                    description = "Builds a docker image containing the Ktor Application."
                    it.commandLine(Docker.Commands.buildExec(Docker.Commands.BUILD))
                    it.dependsOn(DockerizerJarTask.TASK_NAME)
                }

//                tasks.register("dockerRun", Exec::class.java) { execTask ->
                tasks.register("dockerRun") { execTask ->
//                    group = "Docker.GROUP"
                    description = "Runs App inside a Docker Container."
                    execTask.doLast {
                        exec {
                            it.commandLine(Docker.Commands.buildExec(Docker.Commands.RUN_ATTACHED))
                        }
                    }
//                    it.commandLine = listOf()
//                    commandLine(Docker.Commands.buildExec(Docker.Commands.RUN_ATTACHED))
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
