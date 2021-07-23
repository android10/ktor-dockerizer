package com.fernandocejas.ktor

import org.gradle.api.*

abstract class DockerizerPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        setupFatJar(project)
        setupDocker(project)
    }

    /**
     * Reads plugin configuration and sets up FatJar gradle Plugin in order to prepare
     * the artifact to be run inside a docker container.
     */
    private fun setupFatJar(project: Project) {

    }

    /**
     * Sets up the main docker task in order to run Ktor inside a docker container.
     */
    private fun setupDocker(project: Project) {
        project.tasks.register(TASK_RUN_DOCKER, DockerizerDockerTask::class.java) {
            it.tag.set("This is a tag: extension.tag")
            it.message.set("This is a message: extension.message")
        }
    }

    companion object {
        const val TASKS_GROUP = "Ktor Dockerizer"
        const val TASK_RUN_DOCKER = "runDocker"
    }
}
