package openfoodfacts.github.scrachx.openfood.api

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import openfoodfacts.github.scrachx.openfood.api.v0.model.Product
import openfoodfacts.github.scrachx.openfood.api.v0.model.ProductResponse
import openfoodfacts.github.scrachx.openfood.api.v0.service.OpenFoodFactsClient

@Composable
fun SharedMainPreview() {
    val scope = rememberCoroutineScope()
    MainView(
        viewModel = MainViewModel(
            scope = scope,
            client = previewClient
        ),
        modifier = Modifier.fillMaxHeight()
    )
}

private val previewClient = object : OpenFoodFactsClient {
    override suspend fun fetchProductByCode(code: String): ProductResponse {
        return ProductResponse(
            product = Product(productName = "Preview"),
            code = "012345",
            status = 0,
            statusVerbose = "Preview"
        )
    }
}
