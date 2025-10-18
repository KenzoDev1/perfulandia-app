package com.example.perfulandia.ui.cart

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.perfulandia.data.Cart
import com.example.perfulandia.data.CartItem
import com.example.perfulandia.data.Product
import com.example.perfulandia.ui.home.PerfulandiaTopBar
import com.example.perfulandia.ui.theme.PerfulandiaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCartScreen() {
    // Datos de ejemplo que siguen la estructura de tu API.
    val sampleCart by remember {
        mutableStateOf(
            Cart(
                id = 1L,
                userId = 1L,
                items = listOf(
                    CartItem(10L, Product(1, "Versace Eros Flame", 55000.0, 10, ""),1),
                    CartItem(11L, Product(3, "Sauvage Elixir", 110000.0, 30, ""),2)
                )
            )
        )
    }

    // Estado para manejar los cambios en los items del carrito en la UI.
    var cartItems by remember { mutableStateOf(sampleCart.items) }

    Scaffold(
        topBar = { PerfulandiaTopBar() }
    ) { paddingValues ->
        if (cartItems.isEmpty()) {
            // Vista para cuando el carrito está vacío.
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Tu carrito está vacío",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Gray
                )
            }
        } else {
            // Vista principal del carrito con productos.
            Column(
                modifier = Modifier.fillMaxSize().padding(paddingValues)
            ) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Text(
                            "Mi Carrito",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    items(cartItems, key = { it.id }) { cartItem ->
                        CartItemCard(
                            cartItem = cartItem,
                            onQuantityChange = { newQuantity ->
                                // Lógica para actualizar la cantidad de un item.
                                cartItems = cartItems.map {
                                    if (it.id == cartItem.id) it.copy(stock = newQuantity) else it
                                }
                            },
                            onRemoveItem = {
                                // Lógica para eliminar un item.
                                cartItems = cartItems.filter { it.id != cartItem.id }
                            }
                        )
                    }
                }
                OrderSummary(cartItems = cartItems)
            }
        }
    }
}

@Composable
fun CartItemCard(
    cartItem: CartItem,
    onQuantityChange: (Int) -> Unit,
    onRemoveItem: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Placeholder para la imagen del producto.
            Box(
                modifier = Modifier.size(80.dp).border(1.dp, Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Text("Imagen", fontSize = 12.sp, color = Color.Gray)
            }
            Spacer(modifier = Modifier.width(16.dp))
            // Nombre y precio del producto.
            Column(modifier = Modifier.weight(1f)) {
                Text(cartItem.product.name, fontWeight = FontWeight.SemiBold)
                Text(
                    "$${cartItem.product.price.toInt()}",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            // Controles para ajustar cantidad y eliminar.
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { if (cartItem.stock > 1) onQuantityChange(cartItem.stock - 1) }) {
                        Icon(Icons.Filled.Remove, contentDescription = "Restar")
                    }
                    Text(cartItem.stock.toString(), fontWeight = FontWeight.Bold)
                    IconButton(onClick = { onQuantityChange(cartItem.stock + 1) }) {
                        Icon(Icons.Filled.Add, contentDescription = "Aumentar")
                    }
                }
                IconButton(onClick = onRemoveItem, modifier = Modifier.size(24.dp)) {
                    Icon(Icons.Filled.Delete, contentDescription = "Eliminar", tint = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun OrderSummary(cartItems: List<CartItem>) {
    val subtotal = cartItems.sumOf { it.product.price * it.stock }
    val shippingCost = 3500.0 // Valor de ejemplo para el envío.
    val total = subtotal + shippingCost

    ElevatedCard(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Resumen de la orden", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Subtotal")
                Text("$${subtotal.toInt()}")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Costo de envío")
                Text("$${shippingCost.toInt()}")
            }
            Divider(modifier = Modifier.padding(vertical = 12.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total", fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
                Text("$${total.toInt()}", fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* Lógica para proceder al pago */ },
                modifier = Modifier.fillMaxWidth().height(48.dp)
            ) {
                Text("Proceder al Pago", fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true, name = "Carrito con Productos")
@Composable
fun ShoppingCartScreenPreview() {
    PerfulandiaTheme {
        ShoppingCartScreen()
    }
}

@Preview(showBackground = true, name = "Carrito Vacío")
@Composable
fun EmptyShoppingCartScreenPreview() {
    PerfulandiaTheme {
        Scaffold(topBar = { PerfulandiaTopBar() }) { padding ->
            Box(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Tu carrito está vacío",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Gray
                )
            }
        }
    }
}