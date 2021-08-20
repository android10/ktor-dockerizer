package com.fernandocejas.ktor

import org.gradle.api.*
import org.gradle.api.provider.*
import org.gradle.api.tasks.*

/**
 * This task is a wrapper over [ShadowJar] in order to decorate
 * and customize it in order to prepare a jar file to be executed
 * inside a Docker Container.
 */
abstract class DockerizerJarTask : DefaultTask() {

    init {
        description = TASK_DESCRIPTION
        group = DockerizerPlugin.TASK_GROUP
    }

    @get:Input
    abstract val extension: Property<DockerizerExtension>

    @TaskAction
    fun generateJar() {
        val generatedJarFilename = extension.get().jarFilename.get().trim()
        logger.lifecycle("Fat jar '$generatedJarFilename' created in ${project.buildDir}/${generatedJarFilename}")
    }

    companion object {
        const val TASK_NAME = "generateJar"
        private const val TASK_DESCRIPTION = "Generates a FatJar for Ktor."
    }
}
