package com.example.perfulandia.ui.product

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.perfulandia.data.Product
import com.example.perfulandia.ui.home.PerfulandiaTopBar
import com.example.perfulandia.ui.theme.PerfulandiaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(product: Product) {
    // Estado para manejar la cantidad de productos a agregar
    var quantity by remember { mutableStateOf(1) }

    Scaffold(
        topBar = { PerfulandiaTopBar() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // 1. Imagen del producto
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .border(1.dp, Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Imagen del producto", color = Color.Gray)
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // 2. Nombre del producto
            item {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            // 3. Precio del producto
            item {
                Text(
                    text = "$${product.price.toInt()}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // 4. Selector de cantidad
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { if (quantity > 1) quantity-- }) {
                        Icon(Icons.Default.Remove, contentDescription = "Restar cantidad")
                    }
                    Text(
                        text = quantity.toString(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    IconButton(onClick = { quantity++ }) {
                        Icon(Icons.Default.Add, contentDescription = "Aumentar cantidad")
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            // 5. Descripción del producto
            item {
                Text(
                    text = "Descripción",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Aquí va una descripción detallada del perfume, hablando sobre sus notas de salida, corazón y fondo. Es un texto de ejemplo para rellenar el espacio.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(32.dp))
            }

            // 6. Botón de añadir al carrito
            item {
                Button(
                    onClick = { /* Lógica para añadir al carrito */ },
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                ) {
                    Text("Añadir al carrito", fontSize = 16.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailScreenPreview() {
    PerfulandiaTheme {
        val sampleProduct = Product(1, "Versace Eros Flame", 55000.0, 10)
        ProductDetailScreen(product = sampleProduct)
    }
}