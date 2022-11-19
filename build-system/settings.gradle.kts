
// Used to add repositories to both the plugin and dependency management configurations
fun RepositoryHandler.configureCommonRepositories() {
    google()
    mavenCentral()
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
