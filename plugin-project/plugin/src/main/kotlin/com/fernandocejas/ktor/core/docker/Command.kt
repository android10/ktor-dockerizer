package com.fernandocejas.ktor.core.docker

import com.fernandocejas.ktor.extension.DELIMITER

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
