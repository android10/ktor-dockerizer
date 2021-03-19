group = "com.fernandocejas.ktor"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
    applicationName = "ktor-sample"
}

plugins {
    id ("org.jetbrains.kotlin.jvm") version "1.5.0-M1"
    id ("application")
}



buildscript {
    dependencies {
        classpath("com.fernandocejas.gradle:dockerizer")
    }
}

apply(plugin = "com.fernandocejas.gradle:dockerizer")




java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
val compileTestKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions { jvmTarget = "1.8" }
compileTestKotlin.kotlinOptions { jvmTarget = "1.8" }

repositories {
    mavenCentral()
}

dependencies {
    // Application dependencies
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.10")
    implementation("io.ktor:ktor-server-core:1.5.2")
    implementation("io.ktor:ktor-server-netty:1.5.2")
    implementation("ch.qos.logback:logback-classic:1.2.3")
}