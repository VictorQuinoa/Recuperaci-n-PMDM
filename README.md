# EXAMEN 

Para esta aplicación he empleado 3 clases:

Datos: Contiene los datos de la aplicación.
ModelView: Gestiona la lógica.
UI: Da la capacidad de interacción al usuario.

### Datos: 

En esta clase se almacenan los estados de la aplicación y las palabras del juego, contando cada una con un id, un nombre y un sinónimo.

```
enum class Diccionario(val id:Int, val nombre:String, val sinonimo:String) {
    PALABRA1(1, "amor", "afecto"),
    PALABRA2(2, "felicidad", "gozo"),
    PALABRA3( 3, "tristeza", "desconsuelo"),
```

```
enum class Estados (val value: Int, val label: String) {
    INICIO(0, "Inicio"),
    GENERANDO(1, "Generando"),
    JUGANDO(2, "Adivinando"),
    PERDIDO(3, "Perdido"),
    GANADO(4, "Ganado")

}
```



### ModelView:

Esta clase cuenta con la lógica del programa en varios metodos.

- iniciarJuego(), cambia los estados de inicio a generando y asigna la palabra llamando al metodo generarPalabra()

```
fun iniciarJuego() {
        _estado.value = Estados.GENERANDO
        palabraActual = generarPalabra()

        _estado.value = Estados.JUGANDO
    }
```

- generarPalabra(), genera la palabra (guardada en la clase datos) a elegir mediante un número aleatorio correspondiente al id.
```
fun generarPalabra(): Diccionario {
        val palabras = Diccionario.entries.toList()
        return palabras[Random.nextInt(palabras.size)]
    }
```

- verificarRespuesta(), comprueba si la palabra introducida por el usuario en la UI coincide con la generada, cambiando al estado de victoria o perdido.

```
 fun verificarRespuesta(respuesta: String) {
        palabraActual?.let {
            if (respuesta.lowercase() == it.nombre) {
                _estado.value = Estados.GANADO
            } else {

                    _estado.value = Estados.PERDIDO
            }
        }
    }
```

- obtenerPista(), busca el sinónimo en la clase datos de la palabra generada y lo devuelve.

```
 fun obtenerPista(): String {
        return palabraActual?.sinonimo ?: "No hay pista disponible"
    }
    
```


### UI:

La UI observa el cambio en el estado mediante un observer y recibe la respuesta del usuario.

La interfaz muestra la pista y permite al usuario introducir una palabra, que enviará al metodo verificar respuesta para comparar. En caso afirmativo se lanza el mensaje de victoria, en caso de derrota, muestra la palabra generada.

Los diferentes botones de la interfaz se van activando dependiendo del estado de la aplicación, para ser usados solo cuando se deba y no causar fallos.

En caso de perder, se da otra oportunidad de jugar al usuario.
