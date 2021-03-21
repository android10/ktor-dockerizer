pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        jcenter()
    }
}

rootProject.name = "gradle-dockerizer"

include(":sample-project:ktor")
includeBuild("plugin-project") {
    dependencySubstitution {
        substitute(module("com.fernandocejas.gradle:dockerizer")).with(project(":plugin"))
    }
}