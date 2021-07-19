val projectGroup = "Dockerizer"

tasks.register("runKtor") {
    group = projectGroup
    description = "Runs Ktor Sample in a Docker Container."
    dependsOn(":sample-project:ktor:run")
}

tasks.register("runKtorDocker") {
    group = projectGroup
    description = "Runs Ktor Sample in a Docker Container."
    dependsOn(":sample-project:ktor:dockerizerExample")
}