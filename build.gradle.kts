val projectGroup = "Dockerizer"

tasks.named<Wrapper>("wrapper") {
    gradleVersion = BuildPlugins.Versions.gradleVersion
    distributionType = Wrapper.DistributionType.ALL
}

tasks.register("buildProject") {
    group = projectGroup
    description = "Runs App in Development Mode."
    dependsOn("build")
}

tasks.register("runSampleKtor") {
    group = projectGroup
    description = "Runs Ktor Sample in a Docker Container."
    dependsOn(":sample-project:ktor:dockerizerExample")
}

tasks.register("runUnitTests") {
    group = projectGroup
    description = "Runs all Unit Tests in the project."
    dependsOn(":plugin-project:plugin:test")
}