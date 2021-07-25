package com.fernandocejas.ktor

import org.gradle.api.*

abstract class DockerizerPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val extension = project.extensions.create(PLUGIN_CONFIGURATION, DockerizerExtension::class.java)

        setupFatJar(project, extension)
        setupDocker(project, extension)
    }

    /**
     * Reads plugin configuration and sets up FatJar gradle Plugin in order to prepare
     * the artifact to be run inside a docker container.
     */
    private fun setupFatJar(project: Project, pluginExtension: DockerizerExtension) {
        val jarFilename = pluginExtension.jarFilename.get()
        val jarVersion = pluginExtension.jarVersion.get()

        println("This is the jar filename: $jarFilename")
        println("This is the jar version: $jarVersion")
    }

    /**
     * Sets up the main docker task in order to run Ktor inside a docker container.
     */
    private fun setupDocker(project: Project, pluginExtension: DockerizerExtension) {
        project.tasks.register(TASK_RUN_DOCKER, DockerizerDockerTask::class.java) {
            it.tag.set("tag")
            it.message.set("message: ${pluginExtension.jarFilename.get()}")
        }
    }

    companion object {
        // General Plugin Config
        const val PLUGIN_CONFIGURATION = "dockerizer"
        // Plugin Tasks Config
        const val TASK_GROUP = "Ktor Dockerizer"
        const val TASK_RUN_DOCKER = "runDocker"
    }
}
