plugins {
    id ("org.jetbrains.kotlin.jvm") version "1.4.10"
    id ("java-gradle-plugin")
}

java {
    sourceCompatibility = PluginConfiguration.javaCompatibility
    targetCompatibility = PluginConfiguration.javaCompatibility
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

