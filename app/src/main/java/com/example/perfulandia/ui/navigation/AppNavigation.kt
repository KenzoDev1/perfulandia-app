package com.example.perfulandia.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.perfulandia.ui.cart.ShoppingCartScreen
import com.example.perfulandia.ui.home.AboutScreen
import com.example.perfulandia.ui.home.ContactScreen
import com.example.perfulandia.ui.home.HomeScreen
import com.example.perfulandia.ui.home.SearchScreen
import com.example.perfulandia.ui.product.SearchResultsScreen

// 1. Objeto para definir las rutas de forma segura y centralizada.
object AppRoutes {
    const val HOME_SCREEN = "home"
    const val SEARCH_SCREEN = "search"
    const val CART_SCREEN = "cart"
    const val ABOUT_SCREEN = "about"
    const val CONTACT_SCREEN = "contact"
}

// 2. Composable principal que contendrá el NavHost (nuestro mapa de navegación).
@Composable
fun AppNavigation() {
    // 3. Creamos el NavController, que es el que gestiona el estado de la navegación.
    val navController = rememberNavController()

    // 4. Definimos el NavHost, especificando el controlador y la pantalla de inicio.
    NavHost(navController = navController, startDestination = AppRoutes.HOME_SCREEN) {

        // 5. Definimos cada pantalla (composable) con su ruta asociada.
        composable(route = AppRoutes.HOME_SCREEN) {
            // Pasamos el navController a la HomeScreen para que pueda navegar a otras pantallas.
            HomeScreen(navController)
        }

        composable(route = AppRoutes.SEARCH_SCREEN) {
            SearchResultsScreen(navController) // A esta pantalla no le pasamos el navController porque no navega a otros sitios (por ahora).
        }

        composable(route = AppRoutes.CART_SCREEN) {
            ShoppingCartScreen(navController)
        }

        composable(route = AppRoutes.ABOUT_SCREEN) {
            AboutScreen(navController)
        }
        composable(route = AppRoutes.CONTACT_SCREEN) {
            ContactScreen(navController)
        }

        /*
         Aquí puedes agregar el resto de tus pantallas en el futuro.
         Por ejemplo:
         composable(route = "about_screen") {
             AboutScreen()
         }
        */
    }
}