
buildscript {
    dependencies {
        classpath(libs.build.androidgradleplugin)
    }
}

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    `kotlin-dsl`
    alias(libs.plugins.compose) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
}

dependencies {
    implementation(libs.build.kotlingradleplugin)
}

gradlePlugin {
    plugins {
        register("openfoodfacts-build-system") {
            id = "openfoodfacts-build-system"
            implementationClass = "com.openfood.OpenFoodFactsBuildPlugin"
        }
    }
}

group = "openfoodfacts.github.scrachx.openfood.api.client"
version = "1.0-SNAPSHOT"
