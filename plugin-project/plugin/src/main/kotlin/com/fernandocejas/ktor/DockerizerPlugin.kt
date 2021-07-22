package com.fernandocejas.ktor

import org.gradle.api.*

abstract class DockerizerPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        setupFatJar(project)
        setupDocker(project)
    }

    /**
     * TODO
     */
    private fun setupFatJar(project: Project) {
        val extension = project.extensions.create(EXTENSION_NAME, DockerizerExtension::class.java, project)

        // Add a task that uses configuration from the extension object
        project.tasks.register(TASK_NAME, DockerizerExtensionTask::class.java) {
            it.tag.set("This is a tag: extension.tag")
            it.message.set("This is a message: extension.message")
        }
    }

    /**
     * TODO
     */
    private fun setupDocker(project: Project) {

    }

    companion object {
        private const val EXTENSION_NAME = "dockerizerExampleConfig"
        private const val TASK_NAME = "dockerizerExample"
    }
}
