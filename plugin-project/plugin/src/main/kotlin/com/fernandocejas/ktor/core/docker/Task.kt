package com.fernandocejas.ktor.core.docker

import com.fernandocejas.ktor.DockerizerExtension
import com.fernandocejas.ktor.DockerizerPlugin
import com.fernandocejas.ktor.extension.EMPTY
import org.gradle.api.Project
import org.gradle.api.tasks.Exec

abstract class Task(private val project: Project, private val extension: DockerizerExtension) {

    //Mandatory Fields
    abstract val name: String
    abstract val description: String
    abstract val command: Command

    //Optional Fields
    var dependsOn: String = String.EMPTY

    fun register() {
        project.tasks.register(name, Exec::class.java) {
            it.group = DockerizerPlugin.TASK_GROUP
            it.description = description
            it.commandLine(command.forTask())
            if (dependsOn != String.EMPTY) it.dependsOn(dependsOn)
        }
    }
}
