val tasksGroup = "Ktor-Dockerizer Plugin Sample"

tasks.register("runKtor") {
    group = tasksGroup
    description = "Runs Ktor Sample in a Docker Container."
    dependsOn(":sample-project:ktor:run")
}

tasks.register("runKtorDocker") {
    group = tasksGroup
    description = "Runs Ktor Sample in a Docker Container."
    dependsOn(":sample-project:ktor:runDocker")
}
