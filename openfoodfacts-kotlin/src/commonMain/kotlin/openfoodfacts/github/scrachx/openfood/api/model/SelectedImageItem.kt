package openfoodfacts.github.scrachx.openfood.api.model

import kotlinx.serialization.Serializable

@Serializable
data class SelectedImageItem(
    val en: String? = null,
    val fr: String? = null,
    val pl: String? = null,
) {
    val url: String
        get() = listOf(en, fr, pl).firstNotNullOf { it }
}
