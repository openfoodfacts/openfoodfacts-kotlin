# Library Project

## OpenFoodFacts - Kotlin Multiplatform Library


> ⚠️ **This library is in an early development phase**  
> In its current state, this library implements a basic barcode-lookup Product Query via the OpenFoodFacts v0 (Legacy) API .  The aim of this initial implementation is to prove a use-case and API that's already well understood byOpenFoodFacts developers, in Kotlin Multiplatform.  It is hoped that collaborative efforts towards the [current v2 Search API](https://openfoodfacts.github.io/api-documentation/) and other [Editing API's](https://openfoodfacts.github.io/api-documentation/#jump-2READrequests-Getnutritionfactsforaspecificbarcode) will soon follow.

>  ℹ️ **This library is not currently published in any of the popular Maven repositories**  
> If you are looking for an 'easy' Maven coordinate to include this Library as a dependency in your own App, you won't find one yet.
> 
> The library is not in a sufficiently advanced stage of implementation to be published.  
> You can consider publishing to *Maven Local* for early use in your own Apps.

## ⚡️ Quick Start

- To build & publish the Library for consumption by your own Applications:
```bash
# From the repository root
cd openfoodfacts-kotlin
./gradlew publishToMavenLocal
```

Then in your Application's Gradle project, amend these configuration blocks:
```kotlin
repositories {
    // Any other repositories here
    mavenLocal()
}

dependencies {
    // Any other dependencies here
    implementation("openfoodfacts.github.scrachx.openfood.api.client:openfoodfacts-kotlin:1.0-SNAPSHOT")
}
```
Sync your project and you should now be able to resolve the Open Food Facts Client class e.g:
```kotlin
import openfoodfacts.github.scrachx.openfood.api.v0.service.OpenFoodFactsClient 
```
See the [Dev App](../openfoodfacts-kotlin-dev-app/README.md) source code for example usage.

- When building & publishing the Library for consumption by the [Dev App](../openfoodfacts-kotlin-dev-app/README.md), prefer to use:
```bash
# From the repository root
cd openfoodfacts-kotlin
./gradlew publishAllPublicationsToCiRepository
```
...this is detailed in the [Dev App documentation](../openfoodfacts-kotlin-dev-app/README.md).

Both of the above publishing tasks will build the binary artifacts for all supported platforms:
  - **Android** in [AAR format](https://developer.android.com/studio/projects/android-library)
  - **iOS** in [klib format](https://kotlinlang.org/docs/native-libraries.html)
  - **Desktop**/JVM in [JAR format](https://docs.oracle.com/javase/tutorial/deployment/jar/basicsindex.html)

These will be populated into the respective Maven repository, along with the Metadata necessary for a consuming Kotlin Multiplatform project to resolve the Library for each available platform.

## Workflows

### Developing the Library by using the 'Dev App'

For cleanliness, the **Library** and **Dev App** projects are entirely separate.
The **Development App** depends on the **Library** of course, but it does so via a standard Maven artifact dependency, so there are no direct references to the **Library** project or source.

When developing/maintaining the **Library** it's useful to run your changes in the **Development App**.
To assist with this, two types of 'loosely coupled' development workflows are offered:

#### Option 1: Synchronisation through the CI repository

> ℹ️ See [Maven Repository](../repository/maven/ci/README.md)

The folder from the repo-root at `repository/maven/ci` is considered a Maven file-based repository.
As implied, this is used during *Continuous Integration* as a target for:

- Performing a CI check/publish of the **Library** artifacts
- Consuming the **Library** artifacts when performing CI builds of the **Dev App**

This same mechanism is intended to be used on local machines; to publish & consume changes to the **Library** during development.  To do this; simply open two IDE instances e.g. IntelliJ and/or Android Studio:

1. An IDE working with the **Library** at `openfoodfacts-kotlin`
2. An IDE working with the **Dev App** at `openfoodfacts-kotlin-dev-app`

When you have made code changes in the **Library** project that you want to try out, execute:
```
./gradlew publishAllPublicationsToCiRepository
```
...then [rebuild/run the desired **Dev App**](../openfoodfacts-kotlin-dev-app/README.md#running-the-dev-app); building will consume the newly updated **Library** from the CI file repo.

#### Option 2: Using the Gradle Composite workspace

It can be useful for maintainers to develop/debug both projects in the same IDE workspace as though they are one project.
Gradle Composite enables this type of workflow, and so a Composite project is provided here to 'loosely couple' the Library and Development App when it's desirable to work on them simultaneously.

>  ⛔️ **Currently unsupported**  
> While this is expected to become the ideal way of working with the **Library** and **Dev App**,
> Kotlin Multiplatform does not fully support [Gradle Composite builds](https://docs.gradle.org/current/userguide/composite_builds.html) at present.  
> Voting for the relevant YouTrack Issue ([KT-52172](https://youtrack.jetbrains.com/issue/KT-52172)) could help bring this forward!  
