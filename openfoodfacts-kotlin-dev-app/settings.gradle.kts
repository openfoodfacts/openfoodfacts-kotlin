
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
        maven(url = rootDir.parentFile.resolve("repository/maven/ci"))
        configureCommonRepositories()
    }
}

rootProject.name = "openfoodfacts-kotlin-dev-app"

include(
    "shared",
    "android",
    "desktop",
)
// The iOS project should be opened in Xcode
