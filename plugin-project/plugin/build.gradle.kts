plugins {
    id ("org.jetbrains.kotlin.jvm") version "1.4.10"
    id ("java-gradle-plugin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

gradlePlugin {
    plugins {
        create("com.fernandocejas.gradle.dockerizer") {
            id = "com.fernandocejas.gradle.dockerizer"
            implementationClass = "com.fernandocejas.gradle.DockerizerPlugin"
            version = "1.0.0"
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.10")
}

