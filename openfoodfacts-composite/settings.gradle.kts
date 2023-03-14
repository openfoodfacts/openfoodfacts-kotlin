
// Used to add repositories to both the plugin and dependency management configurations
fun RepositoryHandler.configureCommonRepositories() {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev") // For Compose Multiplatform
    maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev") // For Kotlin Dev builds
    maven("https://androidx.dev/storage/compose-compiler/repository/") // For Pre-release Compose

    // This block works around the need for Compose Multiplatform to resolve an androidx Compose Compiler
    // Borrowed from: https://kotlinlang.slack.com/archives/C01D6HTPATV/p1672905437016089?thread_ts=1659657486.021949&cid=C01D6HTPATV
    // This might be removable for future releases
    exclusiveContent {
        forRepository { maven("https://androidx.dev/storage/compose-compiler/repository") }
        filter { includeGroup("androidx.compose.compiler") }
    }
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
