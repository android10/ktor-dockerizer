group = "com.fernandocejas.ktor"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
    applicationName = "ktor-sample"
}

plugins {
    id ("org.jetbrains.kotlin.jvm") version "1.5.0-M1"
    id ("application")

    /**
     * ATTENTION: This is commented in order to facilitate development.
     * It should be uncommented in order to use the plugin.
     * Read below: D E V E L O P M E N T.
     */
//    id ("com.fernandocejas.gradle.dockerizer") version "1.0.0"
}

// --------------------------------------------------------------------------------------------------
// D E V E L O P M E N T     -    R E M O V E     I N    P R O D U C T I O N
// --------------------------------------------------------------------------------------------------
/**
 * In order to do Gradle Dependency Substitution for facilitating Development, this is required.
 * It could be perfectly removed when using in production, by uncommenting the plugin line above
 * in the plugins {} block.
 *
 * To understand better:
 *  - Refer to the `gradle.settings.kts` file in the root project.
 *  - Gradle Composite Builds: https://docs.gradle.org/current/userguide/composite_builds.html
 */
buildscript {
    dependencies {
        classpath("com.fernandocejas.gradle:dockerizer")
    }
}
apply(plugin = "com.fernandocejas.gradle.dockerizer")
// --------------------------------------------------------------------------------------------------

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