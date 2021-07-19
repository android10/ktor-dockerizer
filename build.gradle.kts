val projectGroup = "Dockerizer"

tasks.named<Wrapper>("wrapper") {
    gradleVersion = "6.7"
    distributionType = Wrapper.DistributionType.ALL
}

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