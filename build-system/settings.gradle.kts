
// Used to add repositories to both the plugin and dependency management configurations
fun RepositoryHandler.configureCommonRepositories() {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev") // For Compose Multiplatform
    maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev") // For Kotlin Dev builds
    maven("https://androidx.dev/storage/compose-compiler/repository/") // For Pre-release Compose
}

pluginManagement.repositories {
    gradlePluginPortal()
    configureCommonRepositories()
}

dependencyResolutionManagement {
    repositories {
        configureCommonRepositories()
    }
}

rootProject.name = "openfoodfacts-kotlin-build-system"
