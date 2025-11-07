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

package openfoodfacts.github.scrachx.openfood.api.v0.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a question from Robotoff AI system
 */
@Serializable
data class Question(
    @SerialName("insight_id")
    val insightId: String,
    
    @SerialName("barcode")
    val code: String? = null,
    
    @SerialName("type")
    val type: String? = null,
    
    @SerialName("value")
    val value: String? = null,
    
    @SerialName("question")
    val questionText: String? = null,
    
    @SerialName("insight_type")
    val insightType: String? = null,
    
    @SerialName("source_image_url")
    val sourceImageUrl: String? = null,
    
    @SerialName("image_url")
    val imageUrl: String? = null
) {
    fun isEmpty() = questionText.isNullOrEmpty()
}

/**
 * Represents the state of questions for a product
 */
@Serializable
data class QuestionsState(
    @SerialName("status")
    val status: String? = null,
    
    @SerialName("questions")
    val questions: List<Question>? = null
)

/**
 * Response from annotation API
 */
@Serializable
data class AnnotationResponse(
    @SerialName("status")
    val status: String? = null,
    
    @SerialName("description")
    val description: String? = null,
    
    @SerialName("status_code")
    val statusCode: String? = null
)
