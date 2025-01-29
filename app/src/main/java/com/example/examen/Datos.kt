package com.example.examen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random


object Datos {
    val estadoLiveData: MutableLiveData<Estados> = MutableLiveData(Estados.INICIO)


}


/**
 * Clase que cuenta con las palabras que usa la aplicación
 */
enum class Diccionario(val id:Int, val nombre:String, val sinonimo:String) {
    PALABRA1(1, "amor", "afecto"),
    PALABRA2(2, "felicidad", "gozo"),
    PALABRA3( 3, "tristeza", "desconsuelo"),
    PALABRA4(4, "rapido", "agil"),
    PALABRA5(  5, "lento", "tardío"),
    PALABRA6( 6, "fuerte", "vigoroso"),
    PALABRA7( 7, "debil", "indefenso"),
    PALABRA8(8, "hermoso", "atractivo"),
    PALABRA9(9, "grande", "inmenso"),
    PALABRA10(10, "pequeño", "reducido"),
}

/**
 * Estados de la aplicación
 */

enum class Estados (val value: Int, val label: String) {
    INICIO(0, "Inicio"),
    GENERANDO(1, "Generando"),
    JUGANDO(2, "Adivinando"),
    PERDIDO(3, "Perdido"),
    GANADO(4, "Ganado")

}