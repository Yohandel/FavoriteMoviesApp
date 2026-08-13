package com.cuevas.favoritemoviesapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cuevas.favoritemoviesapp.data.PeliculasRepo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioScreen(
    peliculaId: Int?, // null = agregar nueva, no null = editar existente
    onGuardarClick: () -> Unit,
    onAtrasClick: () -> Unit
) {
    val peliculaExistente = peliculaId?.let { PeliculasRepo.obtenerPorId(it) }

    var nombre by rememberSaveable { mutableStateOf(peliculaExistente?.nombre ?: "") }
    var descripcion by rememberSaveable { mutableStateOf(peliculaExistente?.descripcion ?: "") }
    var imagenUrl by rememberSaveable { mutableStateOf(peliculaExistente?.imagenUrl ?: "") }

    val esEdicion = peliculaExistente != null
    val tituloPantalla = if (esEdicion) "Editar película" else "Agregar película"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(tituloPantalla) },
                navigationIcon = {
                    IconButton(onClick = onAtrasClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )
            OutlinedTextField(
                value = imagenUrl,
                onValueChange = { imagenUrl = it },
                label = { Text("URL de la imagen") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )

            Button( enabled = nombre.isNotBlank() && descripcion.isNotBlank(),
                onClick = {
                    if (nombre.isNotBlank()) {
                        if (esEdicion) {
                            PeliculasRepo.actualizar(peliculaId!!, nombre, descripcion, imagenUrl)
                        } else {
                            PeliculasRepo.agregar(nombre, descripcion, imagenUrl)
                        }
                        onGuardarClick()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                colors = ButtonColors(Color(61, 133, 85),
                    Color.White, Color(85, 105, 91),
                    Color.White )
            ) {
                Text(if (esEdicion) "Guardar cambios" else "Agregar película")
            }
        }
    }
}