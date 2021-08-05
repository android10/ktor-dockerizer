package com.fernandocejas.ktor

import org.gradle.api.provider.*

/**
 * Extension that defines property values for configuring [DockerizerPlugin].
 *
 * @link: https://docs.gradle.org/current/userguide/custom_plugins.html#sec:getting_input_from_the_build
 */
@Suppress("LeakingThis", "UnnecessaryAbstractClass")
abstract class DockerizerExtension {
    abstract val jarFilename: Property<String>
    abstract val jarVersion: Property<String>

    init {
        jarFilename.set(DEFAULT_JAR_FILENAME)
        jarVersion.set(DEFAULT_JAR_VERSION)
    }

    companion object {
        private const val DEFAULT_JAR_FILENAME = "ktor.jar"
        private const val DEFAULT_JAR_VERSION = "0.0.1"
    }
}
