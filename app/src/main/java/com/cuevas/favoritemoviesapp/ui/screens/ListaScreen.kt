package com.cuevas.favoritemoviesapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.cuevas.favoritemoviesapp.data.PeliculasRepo
import com.cuevas.favoritemoviesapp.ui.components.PeliculaCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaScreen(
    onPeliculaClick: (Int) -> Unit,
    onAgregarClick: () -> Unit
) {
    var busqueda by rememberSaveable { mutableStateOf("") }

    val peliculasFiltradas = PeliculasRepo.listaPeliculas.filter {
        it.nombre.contains(busqueda, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Lista de Películas") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAgregarClick, containerColor = Color(61, 133, 85)) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Agregar película",
                    tint = Color.White
                )

            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            OutlinedTextField(
                value = busqueda,
                onValueChange = { busqueda = it },
                label = { Text("Buscar película") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            LazyColumn {
                items(peliculasFiltradas, key = { it.id }) { pelicula ->
                    PeliculaCard(
                        pelicula = pelicula,
                        onClick = { onPeliculaClick(pelicula.id) }
                    )
                }
            }
        }
    }
}