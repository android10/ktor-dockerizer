package com.fernandocejas.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

abstract class DockerizerExtensionTask : DefaultTask() {

    init {
        description = "Just a dockerizer task"
        group = "com.fernandocejas.gradle"
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
        val prettyTag = tag.orNull?.let { "[$it]" } ?: ""

        logger.lifecycle("$prettyTag message is: ${message.orNull}")
        logger.lifecycle("$prettyTag tag is: ${tag.orNull}")

        println("This is Dockerizer")
    }
}