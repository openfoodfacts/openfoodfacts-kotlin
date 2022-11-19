# Open Food Facts - Kotlin

[Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html) package for the [Open Food Facts](https://world.openfoodfacts.org) API. Easily access to more than 1.9 million products from all around the world.
Open Food Facts is powered by contributors from around the world and is constantly growing thanks to them.

## How to use ?

1. See the [Library Documentation](openfoodfacts-kotlin/README.md) to start using this [Multiplatform](https://kotlinlang.org/docs/multiplatform.html) Library in your own Kotlin applications.

2. You might also like to see a [Test Application](openfoodfacts-kotlin-test-app/README.md) which provides and example of the Kotlin Multiplatform Library integrated with iOS, Android and Desktop targets.

3. For maintainers: the **Library** and **Test Application** are separate projects, loosely coupled via a file-based Maven repository. It is useful when further developing either of these, to edit them both in the same IDE workspace.  To enable this; use the [Composite project](openfoodfacts-composite/README.md).

## General principles
- You can look for information about products, including many useful computed values. 
- If you can't get the information on a specific product, you can get your user to send photos and data, that will then be processed by Open Food Facts AI and contributors to get the computed result you want to show them.
- You can also implement the complete flow so that they get immediately the result with some effort on their side.

## Contributing 

### If your users do not expect a specific result immediately (eg. Inventory apps)
- Send photos (front/nutrition/ingredients/packaging): most painless thing for your users
- The Open Food Facts AI Robotoff will generate some derived data from the photos
- Overtime, other apps, and the Open Food Facts community will fill the data gaps

### If your users do expect a result immediately (eg Nutrition apps, Scoring apps…):
- Send nutrition facts + category > get the Nutri-Score
- Send ingredients > get the NOVA group (about food ultra-processing), additives, allergens, normalized ingredients, vegan, vegetarian…
- Send category (strict minimum) + labels + origins of ingredients + packaging (photo and text) > get the Eco-Score (about environmental impact)

## Open Data Licence
The database in under the OdBL. This means attributing the source and also contributing back any additions (photos, data), which this package makes easy to do.
You can check the terms of use here : [Terms of use](https://world.openfoodfacts.org/terms-of-use).

## Third party applications
If you use this SDK, feel free to open a PR to add your application in this list.

## Documentation
This documentation is best viewed and edited using [Obsidian](https://obsidian.md).

## Authors
[Chris Hatton](https://github.com/chris-hatton) (christopherhattonuk@gmail.com)
