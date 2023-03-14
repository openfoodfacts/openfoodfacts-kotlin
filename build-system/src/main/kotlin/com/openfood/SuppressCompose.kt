package com.openfood

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.the

/**
 * The recommended way of suppressing the Compose Compiler version compatibility check is by:
 * ```
 * compose {
 *     ...
 *     kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=<version>")
 * }
 * ```
 * ...however this has become broken for the current Compose version as described here:
 * https://github.com/JetBrains/compose-jb/issues/2734
 *
 * This function implements the 'old way' of suppressing the check in a reusable function,
 * which continues to work; as a workaround.
 */
fun Project.suppressComposeCheckWorkaroundMethod() {
    val libs = the<VersionCatalogsExtension>().named("libs")
    val currentKotlinVersion = libs.findVersion("kotlin").get().requiredVersion
    tasks.withType(org.jetbrains.kotlin.gradle.dsl.KotlinCompile::class.java).configureEach {
        kotlinOptions {
            val suppressionArg =
                "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=$currentKotlinVersion"
            if (!freeCompilerArgs.contains(suppressionArg)) {
                freeCompilerArgs += listOf("-P", suppressionArg)
            }
        }
    }
}