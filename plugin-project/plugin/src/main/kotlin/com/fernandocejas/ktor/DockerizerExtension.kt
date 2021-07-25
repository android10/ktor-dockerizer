package com.fernandocejas.ktor

import org.gradle.api.*
import org.gradle.api.provider.*
import javax.inject.*

/**
 * Extension class that defines property values for configuring [DockerizerPlugin].
 *
 * @link: https://docs.gradle.org/current/userguide/custom_plugins.html#sec:getting_input_from_the_build
 */
@Suppress("LeakingThis")
abstract class DockerizerExtension @Inject constructor(project: Project) {
    abstract val jarBasename: Property<String>
    abstract val jarClassifier: Property<String>
    abstract val jarVersion: Property<String>
    abstract val jarFilename: Property<String>

    init {
        jarBasename.convention("ktor")
        jarClassifier.convention("fat")
        jarVersion.convention("0.0.1")
        jarFilename.convention("ktor-fat.jar")
    }
}
