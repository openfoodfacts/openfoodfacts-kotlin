package openfoodfacts.github.scrach.openfood.api

import io.ktor.client.plugins.logging.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import openfoodfacts.github.scrachx.openfood.api.v0.model.QuestionsState
import openfoodfacts.github.scrachx.openfood.api.v0.service.RobotoffClient
import openfoodfacts.github.scrachx.openfood.api.v0.service.impl.RobotoffKtorClient
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull

@OptIn(ExperimentalCoroutinesApi::class)
class TestRobotoffClient {

    private lateinit var sut: RobotoffClient

    @BeforeTest
    fun setup() {
        sut = RobotoffKtorClient(logLevel = LogLevel.INFO)
    }

    @Test
    fun testGetProductQuestions() = runTest {
        // Using a well-known product barcode (Coca-Cola)
        val questions: QuestionsState = sut.getProductQuestions(
            barcode = "5449000000996",
            langCode = "en",
            count = 5
        )
        
        assertNotNull(questions.status, "Questions state should have a status")
        println("Questions status: ${questions.status}")
        println("Number of questions: ${questions.questions?.size ?: 0}")
        
        questions.questions?.forEach { question ->
            println("Question: ${question.questionText}")
            println("  Type: ${question.type}")
            println("  Value: ${question.value}")
        }
    }
}
