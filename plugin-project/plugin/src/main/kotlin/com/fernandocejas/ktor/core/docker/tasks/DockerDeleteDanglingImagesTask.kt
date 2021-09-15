package com.fernandocejas.ktor.core.docker.tasks

import com.fernandocejas.ktor.DockerizerExtension
import com.fernandocejas.ktor.core.docker.Task
import com.fernandocejas.ktor.core.docker.Task.Command.Companion.fromString
import org.gradle.api.Project

class DockerDeleteDanglingImagesTask(project: Project, extension: DockerizerExtension) : Task(project, extension) {

    // Task overridden fields
    override val name = "dockerDeleteDanglingImages"
    override val description = "Removes/Deletes all the docker dangling images."
    override val command = fromString("docker image prune --filter=dangling=true -f")
}
