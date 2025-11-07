package openfoodfacts.github.scrachx.openfood.api.v0.service

import openfoodfacts.github.scrachx.openfood.api.v0.model.ProductResponse
import openfoodfacts.github.scrachx.openfood.api.v0.model.Search
import openfoodfacts.github.scrachx.openfood.api.v0.model.QuestionsState
import openfoodfacts.github.scrachx.openfood.api.v0.model.AnnotationResponse

/**
 * Main client interface for Open Food Facts API
 */
interface OpenFoodFactsClient {
    /**
     * Returns product by barcode.
     *
     * @param code product barcode
     * @return product response
     */
    suspend fun fetchProductByCode(code: String): ProductResponse

    /**
     * Search products by name
     *
     * @param query search terms
     * @param page page number (starting from 1)
     * @param fields optional comma-separated list of fields to return
     * @return search results
     */
    suspend fun searchProductsByName(
        query: String,
        page: Int = 1,
        fields: String? = null
    ): Search

    /**
     * Get products by category
     *
     * @param category category name
     * @param page page number (starting from 1)
     * @param fields optional comma-separated list of fields to return
     * @return search results
     */
    suspend fun getProductsByCategory(
        category: String,
        page: Int = 1,
        fields: String? = null
    ): Search

    /**
     * Get products by brand
     *
     * @param brand brand name
     * @param page page number (starting from 1)
     * @param fields optional comma-separated list of fields to return
     * @return search results
     */
    suspend fun getProductsByBrand(
        brand: String,
        page: Int = 1,
        fields: String? = null
    ): Search

    /**
     * Get products by label
     *
     * @param label label name (e.g., "organic", "fair-trade")
     * @param page page number (starting from 1)
     * @param fields optional comma-separated list of fields to return
     * @return search results
     */
    suspend fun getProductsByLabel(
        label: String,
        page: Int = 1,
        fields: String? = null
    ): Search

    /**
     * Get products by additive
     *
     * @param additive additive code or name
     * @param page page number (starting from 1)
     * @param fields optional comma-separated list of fields to return
     * @return search results
     */
    suspend fun getProductsByAdditive(
        additive: String,
        page: Int = 1,
        fields: String? = null
    ): Search

    /**
     * Get products by allergen
     *
     * @param allergen allergen name
     * @param page page number (starting from 1)
     * @param fields optional comma-separated list of fields to return
     * @return search results
     */
    suspend fun getProductsByAllergen(
        allergen: String,
        page: Int = 1,
        fields: String? = null
    ): Search

    /**
     * Get products by country
     *
     * @param country country name
     * @param page page number (starting from 1)
     * @param fields optional comma-separated list of fields to return
     * @return search results
     */
    suspend fun getProductsByCountry(
        country: String,
        page: Int = 1,
        fields: String? = null
    ): Search

    /**
     * Get products by packaging type
     *
     * @param packaging packaging type
     * @param page page number (starting from 1)
     * @param fields optional comma-separated list of fields to return
     * @return search results
     */
    suspend fun getProductsByPackaging(
        packaging: String,
        page: Int = 1,
        fields: String? = null
    ): Search

    /**
     * Get products by store
     *
     * @param store store name
     * @param page page number (starting from 1)
     * @param fields optional comma-separated list of fields to return
     * @return search results
     */
    suspend fun getProductsByStore(
        store: String,
        page: Int = 1,
        fields: String? = null
    ): Search

    /**
     * Get products by contributor
     *
     * @param contributor contributor username
     * @param page page number (starting from 1)
     * @param fields optional comma-separated list of fields to return
     * @return search results
     */
    suspend fun getProductsByContributor(
        contributor: String,
        page: Int = 1,
        fields: String? = null
    ): Search

    /**
     * Get incomplete products that need to be completed
     *
     * @param page page number (starting from 1)
     * @param fields optional comma-separated list of fields to return
     * @return search results
     */
    suspend fun getIncompleteProducts(
        page: Int = 1,
        fields: String? = null
    ): Search
}

/**
 * Client interface for Robotoff AI-powered insights
 */
interface RobotoffClient {
    /**
     * Get questions for a product
     *
     * @param barcode product barcode
     * @param langCode language code (e.g., "en", "fr")
     * @param count maximum number of questions to return
     * @return questions state
     */
    suspend fun getProductQuestions(
        barcode: String,
        langCode: String = "en",
        count: Int = 1
    ): QuestionsState

    /**
     * Annotate an insight (answer a question)
     *
     * @param insightId the insight ID
     * @param annotation annotation value (0 = no, 1 = yes, -1 = skip)
     * @return annotation response
     */
    suspend fun annotateInsight(
        insightId: String,
        annotation: Int
    ): AnnotationResponse
}
