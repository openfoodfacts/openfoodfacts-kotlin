package openfoodfacts.github.scrachx.openfood.api

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import openfoodfacts.github.scrachx.openfood.api.model.Ingredient
import openfoodfacts.github.scrachx.openfood.api.service.OpenFoodFactsClient

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModel(
    private val scope: CoroutineScope,
    client: OpenFoodFactsClient
) {

    private val lookupTaps = Channel<Unit>()
    private val barcodeText = MutableStateFlow<String>("3017620425035") // Nutella
    val barcodeTextFlow = barcodeText.asStateFlow()

    val textFlow: StateFlow<String> = lookupTaps
        .consumeAsFlow()
        .flowOn(Dispatchers.Default)
        .map { barcodeText.value }
        .mapLatest { barcode ->
            try {
                client.fetchProductByCode(barcode).product?.run {
                    with(StringBuilder()) {
                        appendLine("Name: ${productName ?: "?"}")
                        appendLine("Weight: ${netWeightValue ?: "?"} ${netWeightUnit ?: "?"}")
                        if(ingredients.isEmpty()) {
                            appendLine("Ingredients unknown")
                        } else {
                            appendLine("Ingredients:")
                            ingredients.forEachIndexed { index, ingredient: Ingredient ->
                                appendLine("$index - ${ingredient.text?.capitalize(Locale.current)}")
                            }
                            appendLine(if(ingredients.all { it.vegetarian == "yes" }) "This product is vegetarian" else "This product is not vegetarian")
                            appendLine(if(ingredients.all { it.vegan == "yes" }) "This product is vegan" else "This product is not vegan")
                        }
                        toString()
                    }
                } ?: "No product found"
            } catch (e: Throwable) {
                e.message ?: ""
            }
        }
        .stateIn(
            scope,
            SharingStarted.Eagerly,
            initialValue = ""
        )

    fun onLookupTapped() = scope.launch {
        lookupTaps.send(Unit)
    }

    fun onBarcodeTextChanged(text: String) {
        barcodeText.value = text
    }
}
