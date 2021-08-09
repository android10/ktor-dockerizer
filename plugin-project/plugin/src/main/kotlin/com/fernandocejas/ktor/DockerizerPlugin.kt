package com.fernandocejas.ktor

import org.gradle.api.*

abstract class DockerizerPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val extension = project.extensions.create(PLUGIN_EXTENSION, DockerizerExtension::class.java)

        setupJar(project, extension)
        setupDocker(project, extension)
    }

    /**
     * Sets up the generate jar task in order to run Ktor through a fat jar.
     */
    private fun setupJar(project: Project, pluginExtension: DockerizerExtension) =
        project.tasks.register(DockerizerJarTask.TASK_NAME, DockerizerJarTask::class.java) {
            it.extension.set(pluginExtension)
        }

    /**
     * Sets up the docker task in order to run Ktor inside a docker container.
     */
    private fun setupDocker(project: Project, pluginExtension: DockerizerExtension) =
        project.tasks.register(DockerizerDockerTask.TASK_NAME, DockerizerDockerTask::class.java) {
            it.extension.set(pluginExtension)
        }

    companion object {
        const val PLUGIN_EXTENSION = "dockerizer"
        const val TASK_GROUP = "Ktor Dockerizer"
    }
}
