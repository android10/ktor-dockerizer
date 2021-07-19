plugins {
    id (BuildPlugins.kotlinJvm) version SourceCode.kotlinVersion
    id (BuildPlugins.javaGradle)
}

java {
    sourceCompatibility = SourceCode.javaCompatibility
    targetCompatibility = SourceCode.javaCompatibility
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