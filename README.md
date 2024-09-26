# Open Food Facts - Kotlin

![Splash](doc/off-kotlin-mpp-title.png)

[Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html) package for the [Open Food Facts](https://world.openfoodfacts.org) API.
Easily access more than 1.9 million products from all around the world.
Open Food Facts is powered by contributors from around the world and is constantly growing thanks to them.

## ⚡ Quick Start    

1. See the library in action by running the Multiplatform [Dev Application](openfoodfacts-kotlin-dev-app/README.md).
2. To start using this Library in your own Kotlin applications, see the [Library Documentation](openfoodfacts-kotlin/README.md).

## 📚 Repository Contents

In order of relevance:

| Folder                          | Description                                      | Documentation                                  |
| ------------------------------- | ------------------------------------------------ | ---------------------------------------------- |
| `/openfoodfacts-kotlin`         | Kotlin Multiplatform Client Library              | [Link](openfoodfacts-kotlin/README.md)         |
| `/openfoodfacts-kotlin-dev-app` | Development Application                          | [Link](openfoodfacts-kotlin-dev-app/README.md) |
| `/openfoodfacts-composite`      | Gradle Composite workspace for Library & Dev App | [Link](openfoodfacts-composite/README.md)      |
| `/scripts`                      | Shell scripts to perform common tasks            | [Link](scripts/README.md)                      |
| `/build-system`                 | Gradle plugin providing common build functions   | [Link](build-system/README.md)                 |
| `/repository/maven/ci`          | File-based Maven repository for CI/Dev usage     | [Link](repository/maven/ci/README.md)          |
| `/doc`                          | Extra Documentation/Assets                       | [Link](doc/README.md)                          |
| `/   `                          | CHANGELOG                                        | [Link](CHANGELOG.md)                           |
## ⚖️ General principles
- You can look for information about products, including many useful computed values. 
- If you can't get the information on a specific product, you can get your user to send photos and data, that will then be processed by Open Food Facts AI and contributors to get the computed result you want to show them.
- You can also implement the complete flow so that they get immediately the result with some effort on their side.

### If your users do not expect a specific result immediately (eg. Inventory apps)
- Send photos (front/nutrition/ingredients/packaging): most painless thing for your users
- The Open Food Facts AI Robotoff will generate some derived data from the photos
- Overtime, other apps, and the Open Food Facts community will fill the data gaps

### If your users do expect a result immediately (eg Nutrition apps, Scoring apps…):
- Send nutrition facts + category > get the Nutri-Score
- Send ingredients > get the NOVA group (about food ultra-processing), additives, allergens, normalized ingredients, vegan, vegetarian…
- Send category (strict minimum) + labels + origins of ingredients + packaging (photo and text) > get the Eco-Score (about environmental impact)

## 💁 Contributing

TBC

## 📄 Open Data Licence
The database in under the OdBL. This means attributing the source and also contributing back any additions (photos, data), which this package makes easy to do.
You can check the terms of use here : [Terms of use](https://world.openfoodfacts.org/terms-of-use).

## 🤝 Third party applications
If you use this SDK, feel free to open a PR to add your application in this list.

<p>We are also very interested in learning what the Open Food Facts data is used for. It is not mandatory, but we would very much appreciate it if you <a href="mailto:reuse@openfoodfacts.org?subject=Open%20Food%20Facts%20Data%20reuse">tell us about your re-uses</a> so that we can share them with the Open Food Facts community. You can also <a href="https://forms.gle/hwaeqBfs8ywwhbTg8">fill this form</a> to get a chance to get your app featured.</p>

## 🧑‍🤝‍🧑 Authors
- [Chris Hatton](https://github.com/chris-hatton) (christopherhattonuk@gmail.com)
## Contributors

[![Drag Racing](https://contrib.rocks/image?repo=openfoodfacts/openfoodfacts-kotlin)](https://github.com/openfoodfacts/openfoodfacts-kotlin/graphs/contributors)
