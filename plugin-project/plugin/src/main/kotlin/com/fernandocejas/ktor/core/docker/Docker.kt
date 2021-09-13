package com.fernandocejas.ktor.core.docker

import com.fernandocejas.ktor.DockerizerExtension
import com.fernandocejas.ktor.core.docker.tasks.DockerBuildImageTask
import com.fernandocejas.ktor.core.docker.tasks.DockerRunTask
import com.fernandocejas.ktor.core.docker.tasks.DockerRunTaskDetached
import com.fernandocejas.ktor.core.docker.tasks.DockerStopContainerTask
import org.gradle.api.Project

/**
 * Docker entry point containing all the logic for setup and register docker tasks
 * belonging to the gradle plugin.
 */
class Docker(private val project: Project, private val extension: DockerizerExtension) {

    /**
     * Sets up the docker task in order to run Ktor inside a docker container.
     */
    fun setupTasks() = createTaskList().map { it.register() }

    private fun createTaskList() =
        listOf(
            DockerBuildImageTask(project, extension),
            DockerRunTask(project, extension),
            DockerRunTaskDetached(project, extension),
            DockerStopContainerTask(project, extension)
        )
}
