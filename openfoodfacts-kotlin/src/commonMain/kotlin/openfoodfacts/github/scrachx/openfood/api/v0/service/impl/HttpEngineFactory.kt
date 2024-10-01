package openfoodfacts.github.scrachx.openfood.api.v0.service.impl

import io.ktor.client.engine.HttpClientEngine

expect open class HttpEngineFactory() {
    open fun create(): HttpClientEngine
}
