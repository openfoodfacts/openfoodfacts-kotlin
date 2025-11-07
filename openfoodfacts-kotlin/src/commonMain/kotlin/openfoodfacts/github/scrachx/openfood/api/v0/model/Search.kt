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
 * Represents a search result from the Open Food Facts API
 */
@Serializable
data class Search(
    @SerialName("page_size")
    val pageSize: String = "0",
    
    @SerialName("count")
    val count: String = "0",
    
    @SerialName("skip")
    val skip: Int = 0,
    
    @SerialName("page")
    val page: Int = 0,
    
    @SerialName("products")
    val products: List<Product> = emptyList()
)
