package openfoodfacts.github.scrachx.openfood.api.v0.service.impl

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal class HttpClientFactory(
    private val engine: HttpClientEngine,
) {

    fun createHttpClient(logLevel: LogLevel): HttpClient = HttpClient(engine) {
        install(DefaultRequest) {
            contentType(ContentType.Application.Json)
        }

        install(ContentNegotiation) {
            json(
                Json {
                    encodeDefaults = true
                    isLenient = true
                    allowSpecialFloatingPointValues = true
                    allowStructuredMapKeys = true
                    prettyPrint = false
                    useArrayPolymorphism = false
                    ignoreUnknownKeys = true
                }
            )
        }

        install(Logging) {
            logger = Logger.SIMPLE
            level = logLevel
        }

        install(HttpRequestRetry)
    }
}
