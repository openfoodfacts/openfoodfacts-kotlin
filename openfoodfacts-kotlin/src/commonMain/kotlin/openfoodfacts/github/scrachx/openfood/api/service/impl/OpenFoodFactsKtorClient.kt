package openfoodfacts.github.scrachx.openfood.api.service.impl

import io.ktor.client.call.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import openfoodfacts.github.scrachx.openfood.api.model.ProductResponse
import openfoodfacts.github.scrachx.openfood.api.service.OpenFoodFactsClient

class OpenFoodFactsKtorClient(
    endpoint: String = API_URL,
    logLevel: LogLevel = LogLevel.NONE
    ): OpenFoodFactsClient {

    private val productQueryUrl = with(URLBuilder(endpoint)) {
        appendPathSegments("product")
        build()
    }

    private fun createProductUrl(code: String): Url = with(URLBuilder(productQueryUrl)) {
        appendPathSegments("$code.json")
        build()
    }

    private val httpClient = HttpClientFactory.createHttpClient(logLevel)

    override suspend fun fetchProductByCode(code: String): ProductResponse =
        httpClient
            .get(
                url = createProductUrl(code),
                block = { header(HttpHeaders.ContentType, ContentType.Application.Json) }
            )
            .body()

    companion object {
        private const val API_URL = "https://world.openfoodfacts.org/api/v0"
    }
}
