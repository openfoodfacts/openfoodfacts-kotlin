# Dev App Project

## OpenFoodFacts Kotlin - Multiplatform Dev Application

The Multiplatform Dev Application shows how you can use the [OpenFoodFacts Kotlin Library](../openfoodfacts-kotlin/README.md) in a [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html) application featuring shared business logic across multiple platforms.

## Features

The Dev App is currently **very** basic:
- User enters a barcode using the soft or hardware keyboard according to platform
- User taps the 'Lookup' button
- After processing, an outcome is displayed. Either:
	- Basic product information -or-
	- a 'No product found' message

This initial implementation is a proof-of-technology and foundation for further work.

## Supported Platforms

### Android

- Use [Android Studio](https://developer.android.com/studio) or [IntelliJ IDEA](https://www.jetbrains.com/idea/) to open the Gradle project at this repository path:
  `/openfoodfacts-kotlin-dev-app`
- You can install the Android App on a Connected Device or Emulator by executing the Gradle task:
  ``

### Desktop

- Use [IntelliJ IDEA](https://www.jetbrains.com/idea/) or  [Android Studio](https://developer.android.com/studio) to open the Gradle project at this repository path:
  `/openfoodfacts-kotlin-dev-app`
- You can run the Desktop App by executing the Gradle task:
  `:desktop:run`
  ...either from the IDE or Command Line

### iOS

- Use Xcode to open the Xcode Project at this repository path:
  `/openfoodfacts-kotlin-dev-app/ios`



