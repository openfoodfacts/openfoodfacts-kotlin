
buildscript {
    dependencies {
        classpath(libs.build.androidgradleplugin)
    }
}

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.compose) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
}

group = "openfoodfacts.github.scrachx.openfood.api.client"
version = "1.0-SNAPSHOT"
