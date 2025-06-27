package com.example.wokwiesp32estacionamento

import AppNavigator
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.wokwiesp32estacionamento.ui.theme.WokwiEsp32EstacionamentoTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            WokwiEsp32EstacionamentoTheme {
                AppNavigator()
            }
        }
    }
}
