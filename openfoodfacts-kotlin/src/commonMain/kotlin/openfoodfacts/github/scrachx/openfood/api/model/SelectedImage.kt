package openfoodfacts.github.scrachx.openfood.api.model

import kotlinx.serialization.Serializable

@Serializable
class SelectedImage {
    val display: SelectedImageItem? = null
    val small: SelectedImageItem? = null
    val thumb: SelectedImageItem? = null
}