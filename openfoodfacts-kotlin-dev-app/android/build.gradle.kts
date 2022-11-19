@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.application")
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose)
}

group = "openfoodfacts.github.scrachx.openfood.api.client"
version = "1.0-SNAPSHOT"

kotlin {
    android()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation(libs.openfoodfacts.kotlin)
                
                implementation(libs.android.appcompat)
                implementation(libs.android.activitycompose)
                
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.ui)

                implementation(libs.ktor.client.logging)
            }
        }
    }
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "openfoodfacts.github.scrachx.openfood.api.client.testapp"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
}
