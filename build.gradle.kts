val tasksGroupKtorSample = "Ktor-Dockerizer Plugin Sample"
val tasksGroupKtorDockerizerPlugin = "Ktor-Dockerizer Plugin Development"

//---------------------------------------------------------------------------------------
// Gradle Tasks for the Sample Project
//---------------------------------------------------------------------------------------
tasks.register("runKtor") {
    group = tasksGroupKtorSample
    description = "Runs Ktor Sample in a Docker Container."
    dependsOn(":sample-project:ktor:run")
}

tasks.register("generateKtorJar") {
    group = tasksGroupKtorSample
    description = "Generates a Jar from the Ktor Sample."
    dependsOn(":sample-project:ktor:generateJar")
}

tasks.register("buildKtorDockerImage") {
    group = tasksGroupKtorSample
    description = "Builds Ktor Sample Docker Image."
    dependsOn(":sample-project:ktor:dockerBuildImage")
}

tasks.register("runKtorDocker") {
    group = tasksGroupKtorSample
    description = "Runs Ktor Sample in a Docker Container."
    dependsOn(":sample-project:ktor:dockerRun")
}

tasks.register("runKtorDockerDetached") {
    group = tasksGroupKtorSample
    description = "Runs Ktor Sample in a Docker Container Detached."
    dependsOn(":sample-project:ktor:dockerRunDetached")
}

tasks.register("stopKtorDockerContainer") {
    group = tasksGroupKtorSample
    description = "Stops Ktor Sample Docker Container."
    dependsOn(":sample-project:ktor:dockerStopContainer")
}

//---------------------------------------------------------------------------------------
// Gradle Tasks for Plugin Development
//---------------------------------------------------------------------------------------
tasks.register("runUnitTests", Exec::class.java) {
    group = tasksGroupKtorDockerizerPlugin
    description = "Runs all the Plugin Unit Tests."
    commandLine("./gradlew -p plugin-project test".split(" "))
}
