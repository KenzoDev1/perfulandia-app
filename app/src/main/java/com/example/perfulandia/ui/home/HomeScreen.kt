package com.example.perfulandia.ui.home

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.perfulandia.model.Product
import com.example.perfulandia.ui.navigation.AppRoutes
import kotlin.math.ceil

@Composable
fun HomeScreen(navController: NavController) {
    val productList = listOf(
        Product(1, "Tommy Hilfinger Impact", 35000.0, 20),
        Product(2, "Versace Eros Flame", 55000.0, 10),
        Product(3, "Sauvage Elixir", 110000.0, 30)
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            FeaturedProductBanner()
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            Text(
                text = "Nuestros productos",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        item {
            val rowCount = ceil(productList.size / 2.0).toInt()
            val gridHeight = rowCount * 220 // Altura aproximada por fila

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .height(gridHeight.dp)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                // Se deshabilita el scroll de la grilla para que LazyColumn lo controle
                userScrollEnabled = false
            ) {
                items(productList.size) { index ->
                    val product = productList[index]
                    ProductCard(product) {
                        navController.navigate("${AppRoutes.PRODUCT_DETAIL_SCREEN}/${product.id}")
                    }
                }
            }
        }
    }
}

// Componentes Reutilizables

@Composable
fun FeaturedProductBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Nombre producto", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {}) {
                Text("Precio")
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .border(1.dp, Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text("Imagen", color = Color.Gray)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(product.name, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
            Text("$${product.price.toInt()}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}


// --- Previews ---

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}