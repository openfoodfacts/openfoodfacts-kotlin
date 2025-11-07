/*
 * Copyright 2016-2020 Open Food Facts
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package openfoodfacts.github.scrachx.openfood.api.v0.service.impl

import io.ktor.client.call.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import openfoodfacts.github.scrachx.openfood.api.v0.model.AnnotationResponse
import openfoodfacts.github.scrachx.openfood.api.v0.model.QuestionsState
import openfoodfacts.github.scrachx.openfood.api.v0.service.RobotoffClient

/**
 * Implementation of RobotoffClient using Ktor HTTP client
 */
class RobotoffKtorClient(
    private val endpoint: String = ROBOTOFF_API_URL,
    private val userAgent: String = DEFAULT_USER_AGENT,
    logLevel: LogLevel = LogLevel.NONE
): RobotoffClient {

    private val httpClient by lazy {
        val engine = HttpEngineFactory().create()
        HttpClientFactory(engine).createHttpClient(logLevel)
    }

    override suspend fun getProductQuestions(
        barcode: String,
        langCode: String,
        count: Int
    ): QuestionsState {
        val url = URLBuilder(endpoint).apply {
            path("api", "v1", "questions", barcode)
            parameters.append("lang", langCode)
            parameters.append("count", count.toString())
        }.build()

        return httpClient.get(url) {
            header(HttpHeaders.UserAgent, userAgent)
        }.body()
    }

    override suspend fun annotateInsight(
        insightId: String,
        annotation: Int
    ): AnnotationResponse {
        val url = URLBuilder(endpoint).apply {
            path("api", "v1", "insights", "annotate")
        }.build()

        return httpClient.post(url) {
            header(HttpHeaders.UserAgent, userAgent)
            setBody(FormDataContent(Parameters.build {
                append("insight_id", insightId)
                append("annotation", annotation.toString())
            }))
        }.body()
    }

    companion object {
        private const val ROBOTOFF_API_URL = "https://robotoff.openfoodfacts.org"
        private const val DEFAULT_USER_AGENT = "OpenFoodFacts Kotlin SDK"
    }
}
