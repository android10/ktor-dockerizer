package com.fernandocejas.ktor

import org.gradle.api.provider.Property

/**
 * Extension that defines property values for configuring [DockerizerPlugin].
 *
 * @link: https://docs.gradle.org/current/userguide/custom_plugins.html#sec:getting_input_from_the_build
 */
@Suppress("LeakingThis", "UnnecessaryAbstractClass")
abstract class DockerizerExtension {
    //Jar
    abstract val jarFilename: Property<String>

    //Docker
    abstract val dockerImageName: Property<String>
    abstract val dockerMemory: Property<String>
    abstract val dockerCpus: Property<String>
    abstract val dockerHostPort: Property<Int>
    abstract val dockerHostSslPort: Property<Int>
    abstract val dockerContainerPort: Property<Int>
    abstract val dockerContainerSslPort: Property<Int>

    init {
        //Jar
        jarFilename.set(JAR_DEFAULT_FILENAME)

        //Docker
        dockerImageName.set(DOCKER_DEFAULT_IMAGE_NAME)
        dockerMemory.set(DOCKER_DEFAULT_MEMORY)
        dockerCpus.set(DOCKER_DEFAULT_CPUS)
        dockerHostPort.set(DOCKER_DEFAULT_HOST_PORT)
        dockerHostSslPort.set(DOCKER_DEFAULT_HOST_SSL_PORT)
        dockerContainerPort.set(DOCKER_DEFAULT_CONTAINER_PORT)
        dockerContainerSslPort.set(DOCKER_DEFAULT_CONTAINER_SSL_PORT)
    }

    companion object {
        //Jar
        private const val JAR_DEFAULT_FILENAME = "ktor.jar"

        //Docker
        private const val DOCKER_DEFAULT_IMAGE_NAME = "ktor"
        private const val DOCKER_DEFAULT_MEMORY = "512M"
        private const val DOCKER_DEFAULT_CPUS = "1"
        private const val DOCKER_DEFAULT_HOST_PORT = 8080
        private const val DOCKER_DEFAULT_HOST_SSL_PORT = 443
        private const val DOCKER_DEFAULT_CONTAINER_PORT = 8080
        private const val DOCKER_DEFAULT_CONTAINER_SSL_PORT = 8443
    }
}
