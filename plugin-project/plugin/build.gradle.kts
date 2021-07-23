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
            group = PluginConfiguration.group
            version = PluginConfiguration.version
            implementationClass = PluginConfiguration.implementationClass
        }
    }
}

dependencies {
    // Application dependencies
    implementation(Libraries.kotlinStd)
}
