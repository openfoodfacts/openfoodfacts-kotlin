package openfoodfacts.github.scrach.openfood.api

import io.ktor.client.plugins.logging.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import openfoodfacts.github.scrachx.openfood.api.model.ProductResponse
import openfoodfacts.github.scrachx.openfood.api.service.OpenFoodFactsClient
import openfoodfacts.github.scrachx.openfood.api.service.impl.OpenFoodFactsKtorClient
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TestOpenFoodFactsClient {

    private lateinit var sut: OpenFoodFactsClient

    @BeforeTest
    fun setup() {
        sut = OpenFoodFactsKtorClient(logLevel = LogLevel.BODY)
    }

    @Test
    fun testWater() = runTest {
        val product: ProductResponse = sut.fetchProductByCode("3274080005003")
        println(product)
    }

}