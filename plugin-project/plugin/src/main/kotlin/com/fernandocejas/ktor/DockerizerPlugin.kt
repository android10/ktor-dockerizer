package com.fernandocejas.ktor

import com.fernandocejas.ktor.core.docker.Docker
import com.fernandocejas.ktor.core.jar.Shadow
import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class DockerizerPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val extension = project.extensions.create(PLUGIN_EXTENSION, DockerizerExtension::class.java)

        project.afterEvaluate {
            Shadow(project, extension).applyPlugin()
            Docker(project, extension).registerTasks()
        }
    }

    companion object {
        const val PLUGIN_EXTENSION = "dockerizer"
        const val TASK_GROUP = "Ktor Dockerizer"
    }
}
