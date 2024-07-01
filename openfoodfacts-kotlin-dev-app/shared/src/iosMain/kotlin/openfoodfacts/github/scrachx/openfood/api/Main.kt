
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Application
import kotlinx.cinterop.autoreleasepool
import kotlinx.cinterop.cstr
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toCValues
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import openfoodfacts.github.scrachx.openfood.api.MainView
import openfoodfacts.github.scrachx.openfood.api.MainViewModel
import openfoodfacts.github.scrachx.openfood.api.v0.service.impl.OpenFoodFactsKtorClient
import platform.Foundation.NSStringFromClass
import platform.UIKit.*

fun main() {
    val args = emptyArray<String>()
    memScoped {
        val argc = args.size + 1
        val argv = args.map { it.cstr.ptr }.toCValues()
        autoreleasepool {
            UIApplicationMain(argc, argv, null, NSStringFromClass(OpenFoodFactsTestAppDelegate))
        }
    }
}

class OpenFoodFactsTestAppDelegate : UIResponder, UIApplicationDelegateProtocol {
    companion object : UIResponderMeta(), UIApplicationDelegateProtocolMeta

    @OverrideInit
    constructor() : super()

    private var _window: UIWindow? = null


    override fun window(): UIWindow? = _window
    override fun setWindow(window: UIWindow?) {
        _window = window
    }

    override fun application(
        application: UIApplication,
        didFinishLaunchingWithOptions: Map<Any?,*>?
    ): Boolean {
        window = UIWindow(frame = UIScreen.mainScreen.bounds).apply {
            rootViewController = Application("OpenFoodFacts") {
                val mainViewModel = MainViewModel(
                    scope = CoroutineScope(Dispatchers.Main + Job()),
                    client = OpenFoodFactsKtorClient()
                )
                MainView(
                    viewModel = mainViewModel,
                    modifier = Modifier.fillMaxSize()
                )
            }
            makeKeyAndVisible()
        }
        return true
    }
}
