package com.fernandocejas.ktor.core.docker.tasks

import com.fernandocejas.ktor.DockerizerExtension
import com.fernandocejas.ktor.core.docker.Task
import com.fernandocejas.ktor.core.docker.Task.Command.Companion.fromString
import org.gradle.api.Project

class DockerRemoveImageTask(project: Project, extension: DockerizerExtension) : Task(project, extension) {

    // Task overridden fields
    override val name = "dockerRemoveImage"
    override val description = "Removes the latest docker image from the Ktor Application."
    override val command = fromString("docker image rm -f ${extension.dockerImageName}")
}
