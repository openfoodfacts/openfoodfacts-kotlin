
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

rootProject.name = "openfoodfacts-composite"

includeBuild("../build-system")
includeBuild("../openfoodfacts-kotlin")
includeBuild("../openfoodfacts-kotlin-dev-app")
