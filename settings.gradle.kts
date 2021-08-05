pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "ktor-dockerizer"
include(":sample-project:ktor")
includeBuild("plugin-project") {
    dependencySubstitution {
        substitute(module("com.fernandocejas.ktor:dockerizer")).using(project(":plugin"))
    }
}
