package com.fernandocejas.ktor.core.docker.tasks

import com.fernandocejas.ktor.DockerizerExtension
import com.fernandocejas.ktor.DockerizerPlugin
import com.fernandocejas.ktor.extension.DELIMITER
import com.fernandocejas.ktor.extension.EMPTY
import org.gradle.api.Project
import org.gradle.api.tasks.Exec

/**
 * Class that represents a Docker task in the context of this Gradle Plugin.
 */
abstract class Task(private val project: Project, private val extension: DockerizerExtension) {

    //Mandatory Fields
    abstract val name: String
    abstract val description: String
    abstract val command: Command

    //Optional Fields
    open var dependsOn: String = String.EMPTY

    fun register() {
        project.tasks.register(name, Exec::class.java) {
            it.group = DockerizerPlugin.TASK_GROUP
            it.description = description
            it.commandLine(command.forTask())
            if (dependsOn != String.EMPTY) it.dependsOn(dependsOn)
        }
    }

    /**
     * Class that represents a command that could be executed via terminal.
     * The purpose of it is to be able to shape commands to be executed
     * by Gradle via command line.
     */
    class Command private constructor(private val command: String) {

        /**
         * In order to register gradle tasks, commands need to be split up into
         * List of Strings. That is what this method does.
         */
        fun forTask() = command.split(String.DELIMITER)

        companion object {
            fun fromString(command: String) = Command(command)
        }
    }
}
