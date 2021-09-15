package com.fernandocejas.ktor.core.docker.tasks

import com.fernandocejas.ktor.DockerizerExtension
import com.fernandocejas.ktor.core.docker.Task
import com.fernandocejas.ktor.core.docker.Task.Command.Companion.fromString
import org.gradle.api.Project

class DockerStopContainerTask(project: Project, extension: DockerizerExtension) : Task(project, extension) {

    // Task overridden fields
    override val name = "dockerStopContainer"
    override val description = "Stops the current docker container running the Ktor Application."
    override val command = fromString("docker stop ${extension.dockerImageName}")
}
