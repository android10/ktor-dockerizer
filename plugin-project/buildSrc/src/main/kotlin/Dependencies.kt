/**
 * Copyright (C) 2021 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.gradle.api.JavaVersion

object PluginConfiguration {
    const val group = "com.fernandocejas.ktor"
    const val name = "dockerizer"
    const val id = group.plus(".").plus(name)
    const val version = "1.0.0"
    const val implementationClass = group.plus(".DockerizerPlugin")
}

object Versions {
    val java = JavaVersion.VERSION_1_8
    const val kotlin = "1.5.21"
    const val shadow = "7.0.0"
}

object BuildPlugins {
    const val kotlinJvm = "org.jetbrains.kotlin.jvm"
    const val javaGradle = "java-gradle-plugin"
}

object Libraries {
    const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val shadowJar = "gradle.plugin.com.github.jengelman.gradle.plugins:shadow:${Versions.shadow}"
}
