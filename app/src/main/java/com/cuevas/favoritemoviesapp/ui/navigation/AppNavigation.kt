package com.cuevas.favoritemoviesapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cuevas.favoritemoviesapp.ui.screens.DetalleScreen
import com.cuevas.favoritemoviesapp.ui.screens.FormularioScreen
import com.cuevas.favoritemoviesapp.ui.screens.InicioScreen
import com.cuevas.favoritemoviesapp.ui.screens.ListaScreen

sealed class Pantalla(val ruta: String) {
    object Inicio : Pantalla("inicio")
    object Lista : Pantalla("lista")
    object Detalle : Pantalla("detalle/{itemId}") {
        fun crearRuta(id: Int) = "detalle/$id"
    }
    object Formulario : Pantalla("formulario?itemId={itemId}") {
        fun crearRutaAgregar() = "formulario"
        fun crearRutaEditar(id: Int) = "formulario?itemId=$id"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Pantalla.Inicio.ruta
    ) {
        composable(Pantalla.Inicio.ruta) {
            InicioScreen(
                onVerListaClick = { navController.navigate(Pantalla.Lista.ruta) }
            )
        }

        composable(Pantalla.Lista.ruta) {
            ListaScreen(
                onPeliculaClick = { id ->
                    navController.navigate(Pantalla.Detalle.crearRuta(id))
                },
                onAgregarClick = {
                    navController.navigate(Pantalla.Formulario.crearRutaAgregar())
                }
            )
        }

        composable(
            route = Pantalla.Detalle.ruta,
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("itemId") ?: 0
            DetalleScreen(
                peliculaId = id,
                onAtrasClick = { navController.popBackStack() },
                onEditarClick = {
                    navController.navigate(Pantalla.Formulario.crearRutaEditar(id))
                },
                onEliminarClick = {
                    navController.popBackStack(Pantalla.Lista.ruta, inclusive = false)
                }
            )
        }

        composable(
            route = Pantalla.Formulario.ruta,
            arguments = listOf(
                navArgument("itemId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("itemId") ?: -1
            FormularioScreen(
                peliculaId = if (id == -1) null else id,
                onGuardarClick = { navController.popBackStack() },
                onAtrasClick = { navController.popBackStack() }
            )
        }
    }
}