package com.fernandocejas.ktor.core.docker

import com.fernandocejas.ktor.DockerizerExtension
import com.fernandocejas.ktor.core.docker.tasks.*
import org.gradle.api.Project

/**
 * Docker entry point containing all the logic for setup and register docker tasks
 * belonging to the gradle plugin.
 */
class Docker(private val project: Project, private val extension: DockerizerExtension) {

    private val taskList = listOf(
        DockerBuildImageTask(project, extension),
        DockerRunTask(project, extension),
        DockerRunTaskDetached(project, extension),
        DockerStopContainerTask(project, extension),
        DockerListImagesTask(project, extension),
        DockerListContainersTask(project, extension),
        DockerRemoveImageTask(project, extension),
        DockerDeleteDanglingImagesTask(project, extension)
    )

    /**
     * Sets up the docker task in order to run Ktor inside a docker container.
     */
    fun registerTasks() = taskList.map { it.register() }
}
