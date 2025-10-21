package com.example.perfulandia.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.perfulandia.ui.navigation.AppNavigation
import com.example.perfulandia.ui.navigation.AppRoutes
import com.example.perfulandia.ui.theme.PerfulandiaTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold() {
    // 1. Controladores para la navegación y el menú lateral
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // 2. Observamos la ruta actual para saber qué item del menú resaltar
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                // 3. Contenido del menú lateral (los botones)
                Text("Perfulandia", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
                Divider()
                NavigationDrawerItem(
                    label = { Text("Inicio") },
                    selected = currentRoute == AppRoutes.HOME_SCREEN,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(AppRoutes.HOME_SCREEN)
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Nosotros") },
                    selected = currentRoute == AppRoutes.ABOUT_SCREEN,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(AppRoutes.ABOUT_SCREEN)
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Contacto") },
                    selected = currentRoute == AppRoutes.CONTACT_SCREEN,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(AppRoutes.CONTACT_SCREEN)
                    }
                )
            }
        }
    ) {
        // 4. El Scaffold principal que contiene la TopBar y el contenido de las pantallas
        Scaffold(
            topBar = {
                PerfulandiaTopBar(
                    onMenuClick = {
                        // El botón de hamburguesa AHORA SIEMPRE abre el menú
                        scope.launch {
                            drawerState.open()
                        }
                    },
                    onSearchClick = { navController.navigate(AppRoutes.SEARCH_SCREEN) },
                    onCartClick = { navController.navigate(AppRoutes.CART_SCREEN) }
                )
            }
        ) { paddingValues ->
            // 5. Aquí se mostrarán todas tus pantallas
            AppNavigation(
                navController,
                Modifier.padding(paddingValues)
            )
        }
    }
}

// Moveremos la TopBar a este archivo, ya que es un componente principal
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfulandiaTopBar(
    onMenuClick: () -> Unit,
    onSearchClick: () -> Unit,
    onCartClick: () -> Unit
) {
    TopAppBar(
        title = { Text("Perfulandia") }, // Título simplificado
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(Icons.Default.Menu, contentDescription = "Menú")
            }
        },
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(Icons.Default.Search, contentDescription = "Buscar")
            }
            IconButton(onClick = onCartClick) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito")
            }
        }
    )
}

@Preview
@Composable
fun MainScaffoldPreview() {
    PerfulandiaTheme {
        MainScaffold()
    }
}