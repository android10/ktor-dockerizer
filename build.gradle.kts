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

tasks.register("buildKtorDocker") {
    group = tasksGroup
    description = "Runs Ktor Sample in a Docker Container."
    dependsOn(":sample-project:ktor:dockerBuildImage")
}

tasks.register("runKtorDocker") {
    group = tasksGroup
    description = "Runs Ktor Sample in a Docker Container."
//    dependsOn(":sample-project:ktor:runDocker")
    dependsOn(":sample-project:ktor:dockerRun")
}
