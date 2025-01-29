package com.example.examen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.examen.Datos

import kotlin.random.Random

import kotlin.uuid.Uuid.Companion.random

class MyViewModel : ViewModel() {


    private val _estado = MutableLiveData(Estados.INICIO)
    val estado: LiveData<Estados> get() = _estado

    private var palabraActual: Diccionario? = null


    /**
     * Metodo que inicia la partida y establece la palabra generada por la maquina
     */
    fun iniciarJuego() {
        _estado.value = Estados.GENERANDO
        palabraActual = generarPalabra()

        _estado.value = Estados.JUGANDO
    }

    fun generarPalabra(): Diccionario {
        val palabras = Diccionario.entries.toList()
        return palabras[Random.nextInt(palabras.size)]
    }

    /**
     * Al introducir una palabra el jugador la analiza y mira si esta en la lista
     */
    fun verificarRespuesta(respuesta: String) {
        palabraActual?.let {
            if (respuesta.lowercase() == it.nombre) {
                _estado.value = Estados.GANADO
            } else {

                    _estado.value = Estados.PERDIDO
            }
        }
    }

    /**
     * Da un sinonimo de la palabra
     */
    fun obtenerPista(): String {
        return palabraActual?.sinonimo ?: "No hay pista disponible"
    }

}
