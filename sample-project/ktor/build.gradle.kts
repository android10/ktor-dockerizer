import com.fernandocejas.ktor.*

// --------------------------------------------------------------------------------------------------
// P R O J E C T     D E P E N D E N C I E S
// --------------------------------------------------------------------------------------------------
private object Dependencies {
    object Versions {
        const val jvmTarget = "1.8"
        const val kotlin = "1.5.21"
        const val ktor = "1.6.1"
        const val logback = "1.2.3"
    }

    object Libraries {
        const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val ktorServerCore = "io.ktor:ktor-server-core:${Versions.ktor}"
        const val ktorServerNetty = "io.ktor:ktor-server-netty:${Versions.ktor}"
        const val logback = "ch.qos.logback:logback-classic:${Versions.logback}"
    }
}

// --------------------------------------------------------------------------------------------------
// P R O J E C T     C O N F I G U R A T I O N
// --------------------------------------------------------------------------------------------------
application {
    mainClass.set("io.ktor.server.netty.EngineMain")
    applicationName = "ktor-sample"
}

plugins {
    id ("org.jetbrains.kotlin.jvm") version "1.5.21"
    id ("application")
}
// --------------------------------------------------------------------------------------------------
// P L U G I N     C O N F I G U R A T I O N
// --------------------------------------------------------------------------------------------------
buildscript {
    dependencies {
        classpath("com.fernandocejas.ktor:dockerizer")
    }
}
apply(plugin = "com.fernandocejas.ktor.dockerizer")

// Configure the extension using a DSL block
configure<DockerizerExtension> {
    jarFilename.set("myKtorProject.jar")
}
// --------------------------------------------------------------------------------------------------

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
val compileTestKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions { jvmTarget = Dependencies.Versions.jvmTarget }
compileTestKotlin.kotlinOptions { jvmTarget = Dependencies.Versions.jvmTarget }

repositories {
    mavenCentral()
}

dependencies {
    // Application dependencies
    implementation(Dependencies.Libraries.kotlinStdLib)
    implementation(Dependencies.Libraries.ktorServerCore)
    implementation(Dependencies.Libraries.ktorServerNetty)
    implementation(Dependencies.Libraries.logback)
}
