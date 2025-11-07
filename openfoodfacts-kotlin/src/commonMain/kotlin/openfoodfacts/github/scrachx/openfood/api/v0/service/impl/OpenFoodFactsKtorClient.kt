package openfoodfacts.github.scrachx.openfood.api.v0.service.impl

import io.ktor.client.call.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import openfoodfacts.github.scrachx.openfood.api.v0.ApiFields
import openfoodfacts.github.scrachx.openfood.api.v0.model.ProductResponse
import openfoodfacts.github.scrachx.openfood.api.v0.model.Search
import openfoodfacts.github.scrachx.openfood.api.v0.service.OpenFoodFactsClient

class OpenFoodFactsKtorClient(
    private val endpoint: String = API_URL,
    private val userAgent: String = DEFAULT_USER_AGENT,
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

    private val httpClient by lazy {
        val engine = HttpEngineFactory().create()
        HttpClientFactory(engine).createHttpClient(logLevel)
    }

    override suspend fun fetchProductByCode(code: String): ProductResponse =
        httpClient
            .get(
                url = createProductUrl(code),
                block = { 
                    header(HttpHeaders.ContentType, ContentType.Application.Json)
                    header(HttpHeaders.UserAgent, userAgent)
                }
            )
            .body()

    override suspend fun searchProductsByName(
        query: String,
        page: Int,
        fields: String?
    ): Search {
        val url = URLBuilder(endpoint).apply {
            path("cgi", "search.pl")
            parameters.append("search_simple", "1")
            parameters.append("json", "1")
            parameters.append("action", "process")
            parameters.append(ApiFields.Keys.SEARCH_TERMS, query)
            parameters.append("page", page.toString())
            fields?.let { parameters.append("fields", it) }
        }.build()

        return httpClient.get(url) {
            header(HttpHeaders.UserAgent, userAgent)
        }.body()
    }

    override suspend fun getProductsByCategory(
        category: String,
        page: Int,
        fields: String?
    ): Search {
        val url = URLBuilder(endpoint).apply {
            path("category", category, "$page.json")
            fields?.let { parameters.append("fields", it) }
        }.build()

        return httpClient.get(url) {
            header(HttpHeaders.UserAgent, userAgent)
        }.body()
    }

    override suspend fun getProductsByBrand(
        brand: String,
        page: Int,
        fields: String?
    ): Search {
        val url = URLBuilder(endpoint).apply {
            path("brand", brand, "$page.json")
            fields?.let { parameters.append("fields", it) }
        }.build()

        return httpClient.get(url) {
            header(HttpHeaders.UserAgent, userAgent)
        }.body()
    }

    override suspend fun getIncompleteProducts(
        page: Int,
        fields: String?
    ): Search {
        val url = URLBuilder(endpoint).apply {
            path("state", "to-be-completed", "$page.json")
            parameters.append("nocache", "1")
            fields?.let { parameters.append("fields", it) }
        }.build()

        return httpClient.get(url) {
            header(HttpHeaders.UserAgent, userAgent)
        }.body()
    }

    companion object {
        private const val API_URL = "https://world.openfoodfacts.org/api/v0"
        private const val DEFAULT_USER_AGENT = "OpenFoodFacts Kotlin SDK"
    }
}
