package com.fernandocejas.ktor.core

import com.fernandocejas.ktor.DockerizerExtension
import com.fernandocejas.ktor.DockerizerPlugin
import com.fernandocejas.ktor.extension.*
import org.gradle.api.Project
import org.gradle.api.tasks.Exec

class Docker(private val project: Project, private val extension: DockerizerExtension) {

    private val config = Config(extension)

    private class Command(private val config: Config) {
        private val runParams =
            "-m${config.dockerMemory} " +
            "--cpus ${config.dockerCpus} -t " +
            "-p ${config.dockerHostPort}:${config.dockerContainerPort} " +
            "-p ${config.dockerHostSslPort}:${config.dockerContainerSslPort} " +
            "-p ${config.dockerContainerSslPort}:${config.dockerContainerSslPort} " +
            "--rm ${config.dockerImageName}"

        private val run = "docker run --name=${config.dockerImageName}"

        val build = buildExec("docker build -t ${config.dockerImageName} .")
        val runAttached = buildExec(run.plus(String.DELIMITER).plus(runParams))
        val runDetached = buildExec(run.plus(String.DELIMITER).plus("-d").plus(String.DELIMITER).plus(runParams))
        val stopContainer = buildExec("docker stop ${config.dockerImageName}")
        val listImages = buildExec("docker image ls")
        val listContainers = buildExec("docker ps -a")
        val removeImage = buildExec("docker image rm -f ${config.dockerImageName}")
        val removeDanglingImages = buildExec("docker image prune --filter=dangling=true -f")

        private fun buildExec(command: String) = command.split(String.DELIMITER)
    }

    /**
     * Sets up the docker task in order to run Ktor inside a docker container.
     */
    fun setupTasks() {
        with(project) {
            tasks.register("dockerBuildImage", Exec::class.java) {
                group = DockerizerPlugin.TASK_GROUP
                description = "Builds a docker image containing the Ktor Application."
                it.commandLine(Commands.buildExec(Commands.BUILD))
                it.dependsOn(Shadow.GENERATE_JAR_TASK_NAME)
            }

            tasks.register("dockerRun", Exec::class.java) {
                group = DockerizerPlugin.TASK_GROUP
                description = "Runs App inside a Docker Container."
                it.commandLine(Commands.buildExec(Commands.RUN_ATTACHED))
            }

            tasks.register("dockerRunDetached", Exec::class.java) {
                group = DockerizerPlugin.TASK_GROUP
                description = "Runs App inside a Docker Container Detached."
                it.commandLine(Commands.buildExec(Commands.RUN_DETACHED))
            }
        }
    }

    private class Config(private val extension: DockerizerExtension) {
        val dockerImageName: String = extension.dockerImageName.get()
        val dockerMemory: String = extension.dockerMemory.get()
        val dockerCpus: String = extension.dockerCpus.get()
        val dockerHostPort: Int = extension.dockerHostPort.get()
        val dockerHostSslPort: Int = extension.dockerHostSslPort.get()
        val dockerContainerPort: Int = extension.dockerContainerPort.get()
        val dockerContainerSslPort: Int = extension.dockerContainerSslPort.get()
    }

    private class Task(private val project: Project, private val name: String,
               private val description: String, private val command: String,
               private val dependsOn: String = String.EMPTY) {

        fun register() {
            project.tasks.register(name, Exec::class.java) {
                it.group = GROUP
                it.description = description
                it.commandLine(command)
                it.dependsOn(dependsOn)
            }
        }

        companion object {
            const val GROUP = DockerizerPlugin.TASK_GROUP
        }
    }
}
