package com.fernandocejas.gradle

import org.gradle.api.Project
import javax.inject.Inject

const val DEFAULT_OUTPUT_FILE = "dockerizer-example.txt"

@Suppress("UnnecessaryAbstractClass")
abstract class DockerizerExtension @Inject constructor(project: Project)