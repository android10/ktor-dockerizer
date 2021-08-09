package com.fernandocejas.ktor

import org.gradle.api.*
import org.gradle.api.provider.*
import org.gradle.api.tasks.*

abstract class DockerizerJarTask : DefaultTask() {

    init {
        description = TASK_DESCRIPTION
        group = DockerizerPlugin.TASK_GROUP
    }

    @get:Input
    abstract val extension: Property<DockerizerExtension>

    @TaskAction
    fun generateJar() {
        println("Jar Filename is: ${extension.get().jarFilename.get()}")
        println("Jar Version is: ${extension.get().jarVersion.get()}")
        println("Generate Ktor Jar!")
    }

    companion object {
        const val TASK_NAME = "generateJar"
        private const val TASK_DESCRIPTION = "Generates a FatJar for Ktor."
    }
}
