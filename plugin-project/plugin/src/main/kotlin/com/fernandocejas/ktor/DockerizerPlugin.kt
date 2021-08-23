package com.fernandocejas.ktor

import com.fernandocejas.ktor.core.Shadow
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Exec

abstract class DockerizerPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val extension = project.extensions.create(PLUGIN_EXTENSION, DockerizerExtension::class.java)
        val shadow = Shadow(project, extension)
        val docker = com.fernandocejas.ktor.core.Docker(project, extension)

        project.afterEvaluate {
            shadow.applyPlugin()
            docker.setupTasks()
        }

        //TODO: Extract this
        setupDockerTasks(project, extension)
    }

    /**
     * Sets up the docker task in order to run Ktor inside a docker container.
     */
    private fun setupDockerTasks(project: Project, pluginExtension: DockerizerExtension) {
        with(project) {
            afterEvaluate {
                tasks.register(DockerizerDockerTask.TASK_NAME, DockerizerDockerTask::class.java) {
                    it.extension.set(pluginExtension)
                    it.dependsOn(Shadow.GENERATE_JAR_TASK_NAME)
                }

                tasks.register("dockerBuildImage", Exec::class.java) {
//                    group = Docker.GROUP
                    description = "Builds a docker image containing the Ktor Application."
                    it.commandLine(Docker.Commands.buildExec(Docker.Commands.BUILD))
                    it.dependsOn(Shadow.GENERATE_JAR_TASK_NAME)
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
        const val PLUGIN_EXTENSION = "dockerizer"
        const val TASK_GROUP = "Ktor Dockerizer"
    }
}
