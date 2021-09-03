package com.fernandocejas.ktor.core

import com.fernandocejas.ktor.DockerizerExtension
import com.fernandocejas.ktor.DockerizerPlugin
import org.gradle.api.Project
import org.gradle.api.tasks.Exec

class Docker(private val project: Project, private val extension: DockerizerExtension) {

    object Config {
        const val MEMORY = "512M"
        const val CPUS = "1"
        const val HOST_PORT = 80
        const val HOST_SSL_PORT = 443
        const val CONTAINER_PORT = 5000
        const val CONTAINER_SSL_PORT = 8443
        const val ARTIFACT = "ktor-fat-jar.jar"
    }

    object Commands {
        private const val DELIMITER = " "
        private const val RUN_PARAMS =
            "-m${Config.MEMORY} " +
                    "--cpus ${Config.CPUS} -t " +
                    "-p ${Config.HOST_PORT}:${Config.CONTAINER_PORT} " +
                    "-p ${Config.HOST_SSL_PORT}:${Config.CONTAINER_SSL_PORT} " +
                    "-p ${Config.CONTAINER_SSL_PORT}:${Config.CONTAINER_SSL_PORT} " +
                    "--rm ${Config.ARTIFACT}"

        private val RUN = "docker run --name=${Config.ARTIFACT}"

        const val BUILD = "docker build -t ${Config.ARTIFACT} ."
        val RUN_ATTACHED = RUN.plus(DELIMITER).plus(RUN_PARAMS)
        val RUN_DETACHED = RUN.plus(DELIMITER).plus("-d").plus(DELIMITER).plus(RUN_PARAMS)
        const val STOP_CONTAINER = "docker stop ${Config.ARTIFACT}"
        const val LIST_IMAGES = "docker image ls"
        const val LIST_CONTAINERS = "docker ps -a"
        const val REMOVE_IMAGE = "docker image rm -f ${Config.ARTIFACT}"
        const val REMOVE_DANGLING_IMAGES = "docker image prune --filter=dangling=true -f"

        fun buildExec(command: String) = command.split(DELIMITER)
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
        }
    }
}
