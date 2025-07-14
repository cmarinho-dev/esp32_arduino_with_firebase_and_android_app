import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wokwiesp32estacionamento.ConfigScreen
import com.example.wokwiesp32estacionamento.MonitorScreen

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "config") {
        composable("config") { ConfigScreen(navController) }
        composable("monitor") { MonitorScreen() }
    }
}
