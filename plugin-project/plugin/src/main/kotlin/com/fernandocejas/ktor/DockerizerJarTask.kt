package com.fernandocejas.ktor

import org.gradle.api.*
import org.gradle.api.tasks.*

abstract class DockerizerJarTask : DefaultTask() {

    init {
        description = TASK_DESCRIPTION
        group = DockerizerPlugin.TASK_GROUP
    }

    @TaskAction
    fun sampleAction() {
        println("Generate Ktor Jar!")
    }

    companion object {
        const val TASK_NAME = "generateJar"
        private const val TASK_DESCRIPTION = "Generates a FatJar for Ktor."
    }
}
