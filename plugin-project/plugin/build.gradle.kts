plugins {
    id (BuildPlugins.kotlinJvm) version Versions.kotlin
    id (BuildPlugins.javaGradle)
}

java {
    sourceCompatibility = Versions.java
    targetCompatibility = Versions.java
}

gradlePlugin {
    plugins {
        create(PluginConfiguration.id) {
            id = PluginConfiguration.id
            implementationClass = PluginConfiguration.implementationClass
            version = PluginConfiguration.version
        }
    }
}

dependencies {
    // Application dependencies
    implementation(Libraries.kotlinStd)
}