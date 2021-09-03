package com.fernandocejas.ktor

import com.fernandocejas.ktor.core.Docker
import com.fernandocejas.ktor.core.Shadow
import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class DockerizerPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val extension = project.extensions.create(PLUGIN_EXTENSION, DockerizerExtension::class.java)
        val shadow = Shadow(project, extension)
        val docker = Docker(project, extension)

        project.afterEvaluate {
            shadow.applyPlugin()
            docker.setupTasks()
        }
    }

    companion object {
        const val PLUGIN_EXTENSION = "dockerizer"
        const val TASK_GROUP = "Ktor Dockerizer"
    }
}
