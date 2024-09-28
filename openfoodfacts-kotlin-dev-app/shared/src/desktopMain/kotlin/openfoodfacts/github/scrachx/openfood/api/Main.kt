package openfoodfacts.github.scrachx.openfood.api

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.ktor.client.plugins.logging.LogLevel
import openfoodfacts.github.scrachx.openfood.api.v0.service.impl.OpenFoodFactsKtorClient

/**
 * Entry point for the Desktop Application
 */
fun main() {
    application {
        Window(
            title = "Open Food Facts Kotlin Dev App",
            onCloseRequest = ::exitApplication,
        ) {
            val scope = rememberCoroutineScope()
            MainView(
                viewModel = MainViewModel(scope, OpenFoodFactsKtorClient(logLevel = LogLevel.ALL)),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

