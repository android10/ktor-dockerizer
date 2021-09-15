package com.fernandocejas.ktor.core.docker.tasks

import com.fernandocejas.ktor.DockerizerExtension
import com.fernandocejas.ktor.core.docker.Task.Command.Companion.fromString
import com.fernandocejas.ktor.extension.DELIMITER
import org.gradle.api.Project

class DockerRunTaskDetached(project: Project, extension: DockerizerExtension) : DockerRunTask(project, extension) {

    // Task overridden fields
    override val name = "dockerRunDetached"
    override val description = "Runs App inside a Docker Container Detached."
    override val command = fromString(run.plus(String.DELIMITER).plus("-d").plus(String.DELIMITER).plus(runParams))
}
