package com.example.wokwiesp32estacionamento

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.database.database

@Composable
fun ConfigScreen(navController: NavController) {
    var vagasMax by remember { mutableStateOf("") }
    var vagasDisp by remember { mutableStateOf("") }

    val database = Firebase.database.reference

    Scaffold { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = vagasMax,
                onValueChange = { vagasMax = it },
                label = { Text("Vagas Máximas") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = vagasDisp,
                onValueChange = { vagasDisp = it },
                label = { Text("Vagas Disponíveis") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                if (vagasMax.isNotEmpty() && vagasDisp.isNotEmpty()) {
                    database.child("vagas_max").setValue(vagasMax.toInt())
                    database.child("vagas_disponiveis").setValue(vagasDisp.toInt())
                    navController.navigate("monitor")
                }
            }) {
                Text("Confirmar")
            }
        }
    }
}
