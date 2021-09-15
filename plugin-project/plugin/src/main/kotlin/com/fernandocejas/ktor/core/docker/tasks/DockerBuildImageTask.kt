package com.fernandocejas.ktor.core.docker.tasks

import com.fernandocejas.ktor.DockerizerExtension
import com.fernandocejas.ktor.core.docker.Task
import com.fernandocejas.ktor.core.docker.Task.Command.Companion.fromString
import com.fernandocejas.ktor.core.jar.Shadow
import org.gradle.api.Project

class DockerBuildImageTask(project: Project, extension: DockerizerExtension) : Task(project, extension) {

    // Task overridden fields
    override val name = "dockerBuildImage"
    override val description = "Builds a docker image containing the Ktor Application."
    override val command = fromString("docker build -t ${extension.dockerImageName} .")
    override var dependsOn = Shadow.GENERATE_JAR_TASK_NAME
}
