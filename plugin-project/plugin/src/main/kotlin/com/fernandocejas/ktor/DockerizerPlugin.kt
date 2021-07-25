package com.fernandocejas.ktor

import org.gradle.api.*

abstract class DockerizerPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val pluginConfig = project.extensions.create(PLUGIN_CONFIGURATION, DockerizerExtension::class.java, project)

        setupFatJar(project, pluginConfig)
        setupDocker(project, pluginConfig)
    }

    /**
     * Reads plugin configuration and sets up FatJar gradle Plugin in order to prepare
     * the artifact to be run inside a docker container.
     */
    private fun setupFatJar(project: Project, pluginConfig: DockerizerExtension) {

    }

    /**
     * Sets up the main docker task in order to run Ktor inside a docker container.
     */
    private fun setupDocker(project: Project, pluginConfig: DockerizerExtension) {
        project.tasks.register(TASK_RUN_DOCKER, DockerizerDockerTask::class.java) {
            it.tag.set("tag")
            it.message.set("message: ${pluginConfig.jarFilename.get()}")
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
