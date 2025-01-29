package com.example.examen

import android.view.View
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel




fun IU(miViewModel: MyViewModel) {

    @Composable
    fun JuegoScreen(viewModel: MyViewModel ) {
        val estado by viewModel.estado.observeAsState(Estados.INICIO)
        var respuesta by remember { mutableStateOf(TextFieldValue()) }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            when (estado) {
                Estados.INICIO -> {
                    Button(onClick = { viewModel.iniciarJuego() }) {
                        Text("Iniciar Juego")
                    }
                }
                Estados.JUGANDO -> {
                    Text("Pista: ${viewModel.obtenerPista()}")
                    BasicTextField(
                        value = respuesta,
                        onValueChange = { respuesta = it },
                        modifier = Modifier.fillMaxWidth().padding(8.dp)
                    )
                    Button(onClick = { viewModel.verificarRespuesta(respuesta.text) }) {
                        Text("Adivinar")
                    }
                }
                Estados.GANADO -> {
                    Text("¡Felicidades! Has ganado.")
                    Button(onClick = { viewModel.iniciarJuego() }) {
                        Text("Jugar de nuevo")
                    }
                }
                Estados.PERDIDO -> {
                    Text("Has perdido. La palabra era: ${viewModel.obtenerPista()}")
                    Button(onClick = { viewModel.iniciarJuego() }) {
                        Text("Intentar de nuevo")
                    }
                }
                else -> {}
            }
        }
    }
}

