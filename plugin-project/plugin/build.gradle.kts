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
        create(PluginConfiguration.name) {
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
    implementation(Libraries.shadowJar)

    // Test dependencies
    testImplementation(TestLibraries.junit4)
    testImplementation(TestLibraries.mockk)
    testImplementation(TestLibraries.kluent)
}
