package com.fernandocejas.ktor.core.docker.tasks

import com.fernandocejas.ktor.DockerizerExtension
import com.fernandocejas.ktor.core.docker.tasks.Task.Command.Companion.fromString
import org.gradle.api.Project

class DockerListImagesTask(project: Project, extension: DockerizerExtension) : Task(project, extension) {

    // Task overridden fields
    override val name = "dockerListImages"
    override val description = "List all the existing Docker Images."
    override val command = fromString("docker image ls")
}
