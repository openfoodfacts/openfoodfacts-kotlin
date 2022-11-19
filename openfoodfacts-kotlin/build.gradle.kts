
buildscript {
    dependencies {
        classpath(libs.build.androidgradleplugin)
    }
}

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.multiplatform)

    id("com.android.library") version libs.versions.build.androidgradleplugin
    alias(libs.plugins.serialization)

    // 'Versions' and 'Updates' plugins enable analysis and semi-automatic update of the project's dependencies
    alias(libs.plugins.versions)
    alias(libs.plugins.updates)

    id("maven-publish")
}

group = "openfoodfacts.github.scrachx.openfood.api.client"
version = "1.0-SNAPSHOT"

kotlin {

    android {
        publishLibraryVariants("debug", "release")
    }

    jvm("desktop")

    setOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries {
            framework {
                baseName = project.name
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.contentnegotiation)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.json)
                implementation(libs.common.serialization.json)
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(libs.ktor.client.java)
            }
        }
        val desktopTest by getting {

        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.test.coroutines)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.android)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(libs.test.kotlin.junit)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
        //val iosTest by getting
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 32
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

repositories {
    mavenLocal()
    mavenCentral()
    google()
}

extensions.getByType<PublishingExtension>().apply {
    /**
     * Publications are defined by the Android Gradle Plugin and Kotlin Multiplatform based
     * on platform and flavour names.
     */
    repositories {
        maven {
            name = "ci"
            url = uri(rootDir.resolve("../repository/maven/ci"))
        }
    }
}
