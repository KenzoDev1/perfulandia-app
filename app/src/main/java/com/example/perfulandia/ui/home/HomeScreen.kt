package com.example.perfulandia.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
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
import com.example.perfulandia.data.Product

// Con @OptIn estamos declarando que vamos a ocupar la libreria Material 3
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val productList = listOf(
        Product(1, "Tommy Hilfinger Impact", 35000.0, 20),
        Product(2, "Versace Eros Flame", 55000.0, 10),
        Product(3, "Sauvage Elixir", 110000.0, 30),
    )
    Scaffold (
        topBar = { PerfulandiaTopBar() }
    ) { paddingValues ->
        // LazyColumn hace que todo el contenido sea deslizable verticalmente
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. Banner de producto destacado
            item {
                FeaturedProductBanner()
                Spacer(modifier = Modifier.height(24.dp))
            }

            // 2. Título de la sección
            item {
                Text(
                    text = "Nuestros productos",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // 3. Grilla de productos
            item {
                // Usamos LazyVerticalGrid dentro de un item de LazyColumn
                // Nota: se le da un alto fijo para que funcione correctamente anidado.
                val gridHeight = (productList.size / 2) * 220 // Ajusta 220.dp al alto de tu Card
                LazyVerticalGrid (
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .height(gridHeight.dp)
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(productList.size) { index ->
                        val product = productList[index]
                        ProductCard(product = product)
                    }
                }
            }
            // 4. Footer (si es parte del scroll)
            item {
                // Aquí podrías agregar el footer si quieres que se deslice con el resto
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfulandiaTopBar() {
    TopAppBar(
        // Centro
        title = {
            // Falta un mutableStateOf(""), se agregara mas adelante
            TextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Buscar en perfulandia") },
                /* ContentDescription proporciona una descripcion textual para personas
                con discapacidad visual, que ocupan lectores de pantallas como
                TalkBack, describiria con la palabra "
                Buscar" al presionar este icono */
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(50)),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        },
        // Izquierda
        navigationIcon = {
            IconButton(onClick = { /* Abrir menú lateral */ }) {
                Icon(Icons.Default.Menu, contentDescription = "Menú")
            }
        },
        // Derecha
        actions = {
            IconButton(onClick = { /* Ir al carrito */ }) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito")
            }
        }
    )
}

@Composable
fun ProductCard(product: Product) {
    Card (
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Placeholder para la imagen del producto
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .border(1.dp, Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                // Aquí iría tu imagen, por ejemplo con la librería Coil:
                // AsyncImage(model = product.imageUrl, contentDescription = product.name)
                Text("Imagen", color = Color.Gray)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(product.name, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
            Text(product.price.toString(), fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

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
        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Nombre producto", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Button (onClick = { /*TODO*/ }) {
                Text("Precio")
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Preview(showBackground = true)
@Composable
fun FeaturedProductBannerPreview(){
    FeaturedProductBanner()
}

@Preview(showBackground = true)
@Composable
fun PerfulandiaTopBarPreview() {
    PerfulandiaTopBar()
}