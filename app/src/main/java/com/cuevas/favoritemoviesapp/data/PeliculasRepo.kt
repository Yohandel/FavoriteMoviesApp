package com.cuevas.favoritemoviesapp.data

import androidx.compose.runtime.mutableStateListOf

object PeliculasRepo {

    private val _peliculas = mutableStateListOf(
        Pelicula(
            id = 1,
            nombre = "Batman: The Dark Knight ",
            descripcion = "Batman, el teniente Jim Gordon y el fiscal de distrito Harvey Dent forman una alianza para destruir el crimen organizado en Gotham City. Sus planes se derrumban con la llegada del Joker, un psicópata anarquista que desata el caos, pone a prueba los límites morales del héroe y corrompe a Dent",
            imagenUrl = "https://www.themoviedb.org/t/p/w1280/qJ2tW6WMUDux911r6m7haRef0WH.jpg"
        ),
        Pelicula(
            id = 2,
            nombre = "Midsommar",
            descripcion = "Varios amigos viajan a Suecia para estudiar, como antropólogos, un festival de verano que se celebra cada noventa años en el remoto pueblo natal de uno de ellos. Lo que comienza como unas vacaciones de ensueño en un lugar donde nunca se pone el sol, se transforma gradualmente en una oscura pesadilla cuando los misteriosos habitantes los invitan a participar en sus inquietantes actividades festivas.",
            imagenUrl = "https://www.themoviedb.org/t/p/w1280/7LEI8ulZzO5gy9Ww2NVCrKmHeDZ.jpg"
        ),
        Pelicula(
            id = 3,
            nombre = "Kimi no na wa (Your Name)",
            descripcion = "Mitsuha y Taki, estudiantes de secundaria, son completos desconocidos que viven vidas separadas. Pero una noche, de repente, intercambian cuerpos. Mitsuha despierta en el cuerpo de Taki, y él en el de ella. Este extraño suceso se repite de forma aleatoria, y ambos deben adaptar sus vidas a la convivencia.",
            imagenUrl = "https://www.themoviedb.org/t/p/w1280/vfJFJPepRKapMd5G2ro7klIRysq.jpg"
        )
    )
    val listaPeliculas: List<Pelicula> get() = _peliculas

    fun obtenerPorId(id: Int): Pelicula? =
        _peliculas.find { it.id == id }

    // CREATE
    fun agregar(nombre: String, descripcion: String, imagenUrl: String) {
        val nuevoId = (_peliculas.maxOfOrNull { it.id } ?: 0) + 1
        _peliculas.add(
            Pelicula(id = nuevoId, nombre = nombre, descripcion = descripcion, imagenUrl = imagenUrl)
        )
    }

    // UPDATE
    fun actualizar(id: Int, nombre: String, descripcion: String, imagenUrl: String) {
        val index = _peliculas.indexOfFirst { it.id == id }
        if (index != -1) {
            _peliculas[index] = Pelicula(id = id, nombre = nombre, descripcion = descripcion, imagenUrl = imagenUrl)
        }
    }

    // DELETE
    fun eliminar(id: Int) {
        _peliculas.removeAll { it.id == id }
    }
}