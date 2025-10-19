package com.example.perfulandia.ui.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.perfulandia.data.Product
import com.example.perfulandia.ui.home.PerfulandiaTopBar
import com.example.perfulandia.ui.home.ProductCard
import com.example.perfulandia.ui.navigation.AppRoutes
import com.example.perfulandia.ui.theme.PerfulandiaTheme
import kotlin.math.ceil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultsScreen(navController: NavController) {
    // Datos de ejemplo para la vista
    val searchedProduct = Product(1, "Versace Eros Flame", 55000.0, 10)
    val relatedProducts = listOf(
        Product(2, "Tommy Hilfinger Impact", 35000.0, 20),
        Product(3, "Sauvage Elixir", 110000.0, 30),
        Product(4, "Otro Perfume A", 45000.0, 15),
        Product(5, "Otro Perfume B", 62000.0, 5),
    )

    Scaffold(
        topBar = { PerfulandiaTopBar(
            onSearchClick = { /* Ya estamos en búsqueda, no hacemos nada */ },
            onCartClick = { navController.navigate(AppRoutes.CART_SCREEN) }
        ) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. Producto buscado (se muestra como un card individual)
            item {
                Spacer(modifier = Modifier.height(16.dp))
                ProductCard(product = searchedProduct)
                Spacer(modifier = Modifier.height(24.dp))
            }

            // 2. Título de la sección de relacionados
            item {
                Text(
                    text = "Productos relacionados",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // 3. Grilla de productos relacionados
            item {
                // Se calcula la altura necesaria para la grilla para que se muestre completa
                val rowCount = ceil(relatedProducts.size / 2.0).toInt()
                val gridHeight = rowCount * 220 // 220.dp es la altura aproximada de cada card

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.height(gridHeight.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(relatedProducts.size) { index ->
                        ProductCard(product = relatedProducts[index])
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchResultsScreenPreview() {
    PerfulandiaTheme {
        val navController = rememberNavController()
        SearchResultsScreen(navController = navController)
    }
}