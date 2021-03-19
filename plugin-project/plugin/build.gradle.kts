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
        create("dockerizerPlugin") {
            id = "com.fernandocejas.gradle:dockerizer"
            implementationClass = "com.fernandocejas.gradle.DockerizerPlugin"
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.10")
//    implementation(gradleApi())
}

