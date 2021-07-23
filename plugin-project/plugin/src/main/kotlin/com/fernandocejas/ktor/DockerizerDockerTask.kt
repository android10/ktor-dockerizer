package com.fernandocejas.ktor

import org.gradle.api.*
import org.gradle.api.provider.*
import org.gradle.api.tasks.*
import org.gradle.api.tasks.options.*
import java.time.*

abstract class DockerizerDockerTask : DefaultTask() {

    init {
        description = "Runs Ktor in a Docker Container."
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
        Duration.ofSeconds(30)
        val prettyTag = tag.orNull?.let { "[$it]" } ?: ""

        logger.lifecycle("$prettyTag message is: ${message.orNull}")
        logger.lifecycle("$prettyTag tag is: ${tag.orNull}")

        println("This is Dockerizer")
    }
}
