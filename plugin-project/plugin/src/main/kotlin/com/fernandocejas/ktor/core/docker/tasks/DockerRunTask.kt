package com.fernandocejas.ktor.core.docker.tasks

import com.fernandocejas.ktor.DockerizerExtension
import com.fernandocejas.ktor.core.docker.tasks.Task.Command.Companion.fromString
import com.fernandocejas.ktor.extension.DELIMITER
import org.gradle.api.Project

open class DockerRunTask(project: Project, extension: DockerizerExtension) : Task(project, extension) {

    val runParams =
        "-m${extension.dockerMemory} " +
        "--cpus ${extension.dockerCpus} -t " +
        "-p ${extension.dockerHostPort}:${extension.dockerContainerPort} " +
        "-p ${extension.dockerHostSslPort}:${extension.dockerContainerSslPort} " +
        "-p ${extension.dockerContainerSslPort}:${extension.dockerContainerSslPort} " +
        "--rm ${extension.dockerImageName}"

    val run = "docker run --name=${extension.dockerImageName}"

    // Task overridden fields
    override val name = "dockerRun"
    override val description = "Runs App inside a Docker Container."
    override val command = fromString(run.plus(String.DELIMITER).plus(runParams))
}
