import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose)
}

kotlin {
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val desktopMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation(libs.coroutines.core)

                implementation(compose.desktop.currentOs)
            }
        }
        val desktopTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        // DMG packaging relies on the `jpackage` tool, present only in Java >=15
        // Java 17 will be used.  This is available on Bitrise CI and JAVA17_HOME is set in `bitrise.yml`.
        // Developers are expected to set JAVA17_HOME for themselves if wanting to package locally.
        val java17HomeVar = "JAVA17_HOME"
        System.getenv(java17HomeVar)
            ?.takeIf { java17Home -> java17Home.isNotBlank() }
            ?.let { java17Home ->
                println("$java17HomeVar variable found, for use by Compose Desktop packaging:\n$java17Home\n")
                javaHome = java17Home
            } ?: run {
                println("Warning: $java17HomeVar variable not set.\nCompose Desktop packaging will only work if your system Java version is already >=15")
            }

        nativeDistributions {
            targetFormats(TargetFormat.Dmg)
            packageName = "Open Food Facts Dev App"
            packageVersion = "1.0.0"
        }
    }
}
