package openfoodfacts.github.scrach.openfood.api

import io.ktor.client.plugins.logging.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import openfoodfacts.github.scrachx.openfood.api.v0.ApiFields
import openfoodfacts.github.scrachx.openfood.api.v0.model.Search
import openfoodfacts.github.scrachx.openfood.api.v0.service.OpenFoodFactsClient
import openfoodfacts.github.scrachx.openfood.api.v0.service.impl.OpenFoodFactsKtorClient
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class TestSearchAndFiltering {

    private lateinit var sut: OpenFoodFactsClient

    @BeforeTest
    fun setup() {
        sut = OpenFoodFactsKtorClient(logLevel = LogLevel.INFO)
    }

    @Test
    fun testSearchProductsByName() = runTest {
        val search: Search = sut.searchProductsByName("nutella", page = 1)
        println("Search results: ${search.count} products found")
        assertTrue(search.products.isNotEmpty(), "Should find products for 'nutella'")
    }

    @Test
    fun testGetProductsByCategory() = runTest {
        val search: Search = sut.getProductsByCategory("beverages", page = 1)
        println("Category search: ${search.count} products in 'beverages'")
        assertTrue(search.products.isNotEmpty(), "Should find products in 'beverages' category")
    }

    @Test
    fun testGetProductsByBrand() = runTest {
        val search: Search = sut.getProductsByBrand("nestle", page = 1)
        println("Brand search: ${search.count} products from 'nestle'")
        assertTrue(search.products.isNotEmpty(), "Should find products from 'nestle' brand")
    }

    @Test
    fun testGetProductsByLabel() = runTest {
        val search: Search = sut.getProductsByLabel("organic", page = 1)
        println("Label search: ${search.count} products with 'organic' label")
        assertTrue(search.products.isNotEmpty(), "Should find products with 'organic' label")
    }

    @Test
    fun testApiFieldsGetAllFields() {
        val fields = ApiFields.getAllFields("en")
        assertTrue(fields.isNotEmpty(), "Should generate field list")
        assertTrue(fields.contains("product_name_en"), "Should include localized product name")
        assertTrue(fields.contains("barcode"), "Should include barcode field")
        println("Generated fields: ${fields.take(100)}...")
    }

    @Test
    fun testApiFieldsLocalizedKeys() {
        val productNameKey = ApiFields.Keys.lcProductNameKey("fr")
        assertTrue(productNameKey == "product_name_fr", "Should create correct localized product name key")
        
        val ingredientsKey = ApiFields.Keys.lcIngredientsKey("es")
        assertTrue(ingredientsKey == "ingredients_text_es", "Should create correct localized ingredients key")
    }
}
