package com.fernandocejas.ktor

import org.gradle.api.*
import org.gradle.api.provider.*
import org.gradle.api.tasks.*
import org.gradle.api.tasks.options.*

abstract class DockerizerDockerTask : DefaultTask() {

    init {
        description = TASK_DESCRIPTION
        group = DockerizerPlugin.TASK_GROUP
    }

    @get:Input
    @get:Option(option = "message", description = "A message to be printed in the default output")
    abstract val message: Property<String>

    @get:Input
    @get:Option(option = "tag", description = "A Tag to be used for debug and in the default output")
    @get:Optional
    abstract val tag: Property<String>

    @TaskAction
    fun sampleAction() {
//            println("Message is: ${message.orNull}")
//            println("Tag is: ${tag.orNull}")
        println("This is Ktor Dockerizer")
    }

    companion object {
        const val TASK_NAME = "runDocker"
        private const val TASK_DESCRIPTION = "Runs Ktor in a Docker Container."
    }
}
