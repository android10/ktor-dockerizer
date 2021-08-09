package com.fernandocejas.ktor

import org.gradle.api.*
import org.gradle.api.provider.*
import org.gradle.api.tasks.*

abstract class DockerizerDockerTask : DefaultTask() {

    init {
        description = TASK_DESCRIPTION
        group = DockerizerPlugin.TASK_GROUP
    }

    @get:Input
    abstract val extension: Property<DockerizerExtension>

    @TaskAction
    fun runDocker() {
        println("This is Ktor Dockerizer")
    }

    companion object {
        const val TASK_NAME = "runDocker"
        private const val TASK_DESCRIPTION = "Runs Ktor in a Docker Container."
    }
}
