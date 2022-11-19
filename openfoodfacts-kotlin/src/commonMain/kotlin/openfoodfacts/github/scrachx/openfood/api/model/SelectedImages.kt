package openfoodfacts.github.scrachx.openfood.api.model

import kotlinx.serialization.Serializable

@Serializable
data class SelectedImages(
    private val front: SelectedImage? = null,
    private val ingredients: SelectedImage? = null,
    private val nutrition: SelectedImage? = null,
)
