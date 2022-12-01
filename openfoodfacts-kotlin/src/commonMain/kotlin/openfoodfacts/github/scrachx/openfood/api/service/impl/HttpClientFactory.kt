package openfoodfacts.github.scrachx.openfood.api.service.impl

import io.ktor.client.*
import io.ktor.client.plugins.logging.*

expect object HttpClientFactory {
    fun createHttpClient(logLevel: LogLevel): HttpClient
}