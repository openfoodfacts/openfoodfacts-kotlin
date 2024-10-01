package openfoodfacts.github.scrachx.openfood.api.v0.service.impl

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.java.Java

actual open class HttpEngineFactory {

    actual open fun create(): HttpClientEngine {
        return Java.create()
    }
}
