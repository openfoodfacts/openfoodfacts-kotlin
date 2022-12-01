package openfoodfacts.github.scrachx.openfood.api

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Note: Composable functions in shared code must be `internal` due to
 * current limitations of Compose/iOS on Kotlin/Native.
 */
@Composable
internal fun MainView(
    viewModel: MainViewModel,
    modifier: Modifier
) {
    Column(
        modifier = modifier.padding(top = 64.dp, start = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Open Food Facts", style = MaterialTheme.typography.h3)
        Text("Barcode lookup", style = MaterialTheme.typography.h5)
        val barcodeText = viewModel.barcodeTextFlow.collectAsState()
        TextField(barcodeText.value, onValueChange = viewModel::onBarcodeTextChanged)
        Button(onClick = viewModel::onLookupTapped) {
            Text("Lookup")
        }
        val text = viewModel.textFlow.collectAsState()
        Text(text.value)
    }
}
