package openfoodfacts.github.scrachx.openfood.api

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import openfoodfacts.github.scrachx.openfood.api.service.impl.OpenFoodFactsKtorClient
import androidx.compose.foundation.layout.fillMaxSize

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        val mainViewModel = MainViewModel(
            scope = lifecycleScope,
            client = OpenFoodFactsKtorClient()
        )

        setContent {
            MainView(
                viewModel = mainViewModel,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
