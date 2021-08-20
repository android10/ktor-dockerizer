package com.fernandocejas.ktor

object Docker {
    const val GROUP = "Docker"

    object Config {
        const val MEMORY = "512M"
        const val CPUS = "1"
        const val HOST_PORT = 80
        const val HOST_SSL_PORT = 443
        const val CONTAINER_PORT = 5000
        const val CONTAINER_SSL_PORT = 8443
        const val ARTIFACT = "ktor-fat-jar.jar"
    }

    object Commands {
        private const val DELIMITER = " "
        private const val RUN_PARAMS =
            "-m${Config.MEMORY} " +
            "--cpus ${Config.CPUS} -t " +
            "-p ${Config.HOST_PORT}:${Config.CONTAINER_PORT} " +
            "-p ${Config.HOST_SSL_PORT}:${Config.CONTAINER_SSL_PORT} " +
            "-p ${Config.CONTAINER_SSL_PORT}:${Config.CONTAINER_SSL_PORT} " +
            "--rm ${Config.ARTIFACT}"

        private val RUN = "docker run --name=${Config.ARTIFACT}"

        const val BUILD = "docker build -t ${Config.ARTIFACT} ."
        val RUN_ATTACHED = RUN.plus(DELIMITER).plus(RUN_PARAMS)
        val RUN_DETACHED = RUN.plus(DELIMITER).plus("-d").plus(DELIMITER).plus(RUN_PARAMS)
        const val STOP_CONTAINER = "docker stop ${Config.ARTIFACT}"
        const val LIST_IMAGES = "docker image ls"
        const val LIST_CONTAINERS = "docker ps -a"
        const val REMOVE_IMAGE = "docker image rm -f ${Config.ARTIFACT}"
        const val REMOVE_DANGLING_IMAGES = "docker image prune --filter=dangling=true -f"

        fun buildExec(command: String) = command.split(DELIMITER)
//        fun log(command: String) = ""
    }
}
