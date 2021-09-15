package com.fernandocejas.ktor.core.docker.tasks

import com.fernandocejas.ktor.DockerizerExtension
import com.fernandocejas.ktor.core.Task
import com.fernandocejas.ktor.core.Task.Command.Companion.fromString
import org.gradle.api.Project

class DockerListContainersTask(project: Project, extension: DockerizerExtension) : Task(project, extension) {

    // Task overridden fields
    override val name = "dockerListContainers"
    override val description = "List all the running Docker Containers."
    override val command = fromString("docker ps -a")
}
