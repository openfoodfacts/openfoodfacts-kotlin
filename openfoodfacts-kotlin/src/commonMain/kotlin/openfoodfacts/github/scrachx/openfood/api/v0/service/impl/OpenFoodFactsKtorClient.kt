package openfoodfacts.github.scrachx.openfood.api.v0.service.impl

import io.ktor.client.call.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import openfoodfacts.github.scrachx.openfood.api.v0.ApiFields
import openfoodfacts.github.scrachx.openfood.api.v0.model.ProductResponse
import openfoodfacts.github.scrachx.openfood.api.v0.model.Search
import openfoodfacts.github.scrachx.openfood.api.v0.service.OpenFoodFactsClient

/**
 * Filter types supported by the Open Food Facts API
 */
private enum class FilterType(val value: String) {
    CATEGORY("category"),
    BRAND("brand"),
    LABEL("label"),
    ADDITIVE("additive"),
    ALLERGEN("allergen"),
    COUNTRY("country"),
    PACKAGING("packaging"),
    STORE("store")
}

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
    ): Search = getProductsByFilter(FilterType.CATEGORY, category, page, fields)

    override suspend fun getProductsByBrand(
        brand: String,
        page: Int,
        fields: String?
    ): Search = getProductsByFilter(FilterType.BRAND, brand, page, fields)

    override suspend fun getProductsByLabel(
        label: String,
        page: Int,
        fields: String?
    ): Search = getProductsByFilter(FilterType.LABEL, label, page, fields)

    override suspend fun getProductsByAdditive(
        additive: String,
        page: Int,
        fields: String?
    ): Search = getProductsByFilter(FilterType.ADDITIVE, additive, page, fields)

    override suspend fun getProductsByAllergen(
        allergen: String,
        page: Int,
        fields: String?
    ): Search = getProductsByFilter(FilterType.ALLERGEN, allergen, page, fields)

    override suspend fun getProductsByCountry(
        country: String,
        page: Int,
        fields: String?
    ): Search = getProductsByFilter(FilterType.COUNTRY, country, page, fields)

    override suspend fun getProductsByPackaging(
        packaging: String,
        page: Int,
        fields: String?
    ): Search = getProductsByFilter(FilterType.PACKAGING, packaging, page, fields)

    override suspend fun getProductsByStore(
        store: String,
        page: Int,
        fields: String?
    ): Search = getProductsByFilter(FilterType.STORE, store, page, fields)

    override suspend fun getProductsByContributor(
        contributor: String,
        page: Int,
        fields: String?
    ): Search {
        val url = URLBuilder(endpoint).apply {
            path("contributor", contributor, "$page.json")
            parameters.append("nocache", "1")
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

    /**
     * Helper function to get products by a specific filter type
     */
    private suspend fun getProductsByFilter(
        filterType: FilterType,
        filterValue: String,
        page: Int,
        fields: String?
    ): Search {
        val url = URLBuilder(endpoint).apply {
            path(filterType.value, filterValue, "$page.json")
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
