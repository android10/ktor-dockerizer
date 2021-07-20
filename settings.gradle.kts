pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "gradle-dockerizer"
include(":sample-project:ktor")
includeBuild("plugin-project") {
    dependencySubstitution {
        substitute(module("com.fernandocejas.ktor:dockerizer")).with(project(":plugin"))
    }
}