package com.example.perfulandia.ui.product

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
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
import com.example.perfulandia.ui.home.ProductCard // Asegúrate de importar tu ProductCard
import com.example.perfulandia.ui.navigation.AppRoutes
import com.example.perfulandia.ui.theme.PerfulandiaTheme
import kotlin.math.ceil

@Composable
fun SearchResultsScreen(navController: NavController) { // <-- 1. Recibe el NavController
    val searchedProduct = Product(1, "Versace Eros Flame", 55000.0, 10)
    val relatedProducts = listOf(
        Product(2, "Tommy Hilfinger Impact", 35000.0, 20),
        Product(3, "Sauvage Elixir", 110000.0, 30),
        Product(4, "Otro Perfume A", 45000.0, 15),
        Product(5, "Otro Perfume B", 62000.0, 5),
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            // 2. Añade la acción onClick
            ProductCard(product = searchedProduct) {
                navController.navigate("${AppRoutes.PRODUCT_DETAIL_SCREEN}/${searchedProduct.id}")
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            Text(
                text = "Productos relacionados",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            val rowCount = ceil(relatedProducts.size / 2.0).toInt()
            val gridHeight = rowCount * 220

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.height(gridHeight.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                userScrollEnabled = false
            ) {
                items(relatedProducts.size) { index ->
                    val product = relatedProducts[index]
                    // 3. Añade la acción onClick aquí también
                    ProductCard(product = product) {
                        navController.navigate("${AppRoutes.PRODUCT_DETAIL_SCREEN}/${product.id}")
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
        // 4. Actualiza el Preview para que no dé error
        val navController = rememberNavController()
        SearchResultsScreen(navController = navController)
    }
}