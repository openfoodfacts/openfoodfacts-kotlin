
plugins {
    id("openfoodfacts-build-system")
    alias(libs.plugins.kotlin.multiplatform) apply false

    // 'Versions' and 'Updates' plugins enable analysis and semi-automatic update of the project's dependencies
    alias(libs.plugins.versions)
    alias(libs.plugins.updates)
}

buildscript {
    dependencies {
        classpath(libs.build.androidgradleplugin)
    }
}
