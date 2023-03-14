package openfoodfacts.github.scrachx.openfood.api.v0.service

import openfoodfacts.github.scrachx.openfood.api.v0.model.ProductResponse

interface OpenFoodFactsClient {
    /**
     * Returns product by code.
     *
     * @param code product code
     * @return product
     */
    suspend fun fetchProductByCode(code: String): ProductResponse
}
