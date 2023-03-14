package com.openfood

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * This plugin itself currently does nothing; but loading it brings all other files in this module
 * into the main project's build classpath.
 *
 * This effectively making our `build-system` folder behave like `buildSrc`.
 *
 * A major benefit of this approach over using `buildSrc` is that changes to this plugins source
 * will not cause the main projects build cache to be invalidated; which is the behaviour when
 * editing `buildSrc`.  This entails a major productivity boost over `buildSrc` when making
 * iterative changes to configuration.
 *
 * See: [How to use Composite builds as a replacement of buildSrc in Gradle](https://medium.com/bumble-tech/how-to-use-composite-builds-as-a-replacement-of-buildsrc-in-gradle-64ff99344b58)
 */
class OpenFoodFactsBuildPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        // No-op
    }
}

