import org.jetbrains.kotlin.gradle.plugin.mpp.BitcodeEmbeddingMode
import com.openfood.suppressComposeCheckWorkaroundMethod

val iosDeploymentTarget = "15.4"

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose)
}

kotlin {

    android {
        publishLibraryVariants("release", "debug")
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    setOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries {
            framework {
                // Required by Compose/iOS
                embedBitcode = BitcodeEmbeddingMode.DISABLE
                baseName = project.name
                freeCompilerArgs = freeCompilerArgs + listOf(
                    // Linking requirements gleaned from: https://github.com/JetBrains/compose-jb/blob/master/experimental/examples/falling-balls-mpp/build.gradle.kts
                    "-linker-option", "-framework", "-linker-option", "Metal",
                    "-linker-option", "-framework", "-linker-option", "CoreText",
                    "-linker-option", "-framework", "-linker-option", "CoreGraphics",
                    // Disabling Bitcode verification is necessary for Compose/iOS, its binaries confuse LLVM.
                    "-Xdisable-phases=VerifyBitcode",
                )
            }
        }
        iosTarget.compilations.all {
            // If we don't specify this; then the toolchain defaults to targeting iOS 9.0, which fails for Compose/iOS
            kotlinOptions.freeCompilerArgs += "-Xoverride-konan-properties=osVersionMin.ios_x64=$iosDeploymentTarget;osVersionMin.ios_arm64=$iosDeploymentTarget"
        }
    }

    jvm("desktop")

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.openfoodfacts.kotlin)

                implementation(compose.ui)
                implementation(compose.runtime)
                implementation(compose.material)
                implementation(compose.foundation)
                implementation(libs.coroutines.core)

                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.logging)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
            // See `android` block for dependencies
        }
        val androidTest by getting
        val desktopMain by getting {
            dependencies {
                implementation(compose.uiTooling)
                implementation(compose.preview)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            setOf(
                iosX64Main,
                iosArm64Main,
                iosSimulatorArm64Main,
            ).forEach { iosTarget ->
                iosTarget.dependsOn(this)
            }
        }
        val previewMain by creating {
            dependsOn(commonMain)
            desktopMain.dependsOn(this)
            androidMain.dependsOn(this)
        }
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.jetpack.get()
    }

    dependencies {
        debugImplementation(libs.compose.android.uitooling.core)
        implementation(libs.compose.android.uitooling.preview)

        implementation(libs.android.appcompat)
        implementation(libs.android.activitycompose)
    }
}

compose {
    android {
        useAndroidX = true
    }

    kotlinCompilerPlugin.set(libs.compose.compiler.jetbrainsmultiplatform.get().toString())

    // Broken in this version due to: https://github.com/JetBrains/compose-jb/pull/2716
    // Use this method later (replacing `suppressComposeCheckWorkaroundMethod`)
    // kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=${libs.versions.kotlin}")
}

suppressComposeCheckWorkaroundMethod()
