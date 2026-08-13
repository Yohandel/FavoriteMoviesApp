package com.cuevas.favoritemoviesapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cuevas.favoritemoviesapp.data.PeliculasRepo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleScreen(
    peliculaId: Int,
    onAtrasClick: () -> Unit,
    onEditarClick: () -> Unit,
    onEliminarClick: () -> Unit
) {
    val pelicula = PeliculasRepo.obtenerPorId(peliculaId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(pelicula?.nombre ?: "Detalle") },
                navigationIcon = {
                    IconButton(onClick = onAtrasClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        if (pelicula == null) {
            Text("Película no encontrada", modifier = Modifier.padding(padding).padding(16.dp))
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            AsyncImage(
                model = pelicula.imagenUrl,
                contentDescription = pelicula.nombre,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
            )
            Text(
                text = pelicula.nombre,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = pelicula.descripcion,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onEditarClick,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB7A538), contentColor = Color.White)
                ) {
                    Text("Editar")
                }
                Button(
                    onClick = {
                        PeliculasRepo.eliminar(pelicula.id)
                        onEliminarClick()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB3261E), contentColor = Color.White),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Eliminar")
                }
            }
        }
    }
}