val tasksGroup = "Ktor-Dockerizer Plugin Sample"

tasks.register("runKtor") {
    group = tasksGroup
    description = "Runs Ktor Sample in a Docker Container."
    dependsOn(":sample-project:ktor:run")
}

tasks.register("generateKtorJar") {
    group = tasksGroup
    description = "Generates a Jar from the Ktor Sample."
    dependsOn(":sample-project:ktor:generateJar")
}

tasks.register("buildKtorDockerImage") {
    group = tasksGroup
    description = "Builds Ktor Sample Docker Image."
    dependsOn(":sample-project:ktor:dockerBuildImage")
}

tasks.register("runKtorDocker") {
    group = tasksGroup
    description = "Runs Ktor Sample in a Docker Container."
    dependsOn(":sample-project:ktor:dockerRun")
}

tasks.register("runKtorDockerDetached") {
    group = tasksGroup
    description = "Runs Ktor Sample in a Docker Container Detached."
    dependsOn(":sample-project:ktor:dockerRunDetached")
}

tasks.register("stopKtorDockerContainer") {
    group = tasksGroup
    description = "Stops Ktor Sample Docker Container."
    dependsOn(":sample-project:ktor:dockerStopContainer")
}
