package openfoodfacts.github.scrachx.openfood.api.model

import kotlinx.serialization.Serializable

@Serializable
data class LanguagesCodes(
    val en: String? = null,
    val fr: String? = null,
    val pl: String? = null,
)
