package com.example.perfulandia.ui.seller

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.perfulandia.ui.theme.PerfulandiaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterProductScreen() {
    var productName by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var categoryExpanded by remember { mutableStateOf(false) }
    val categories = listOf("Hombre", "Mujer", "Unisex")
    var selectedCategory by remember { mutableStateOf(categories[0]) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Perfulandia - Vendedor") },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Open drawer */ }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                }
            )
        },
        bottomBar = {
            // Asumo una navegación similar a la de Admin
            BottomAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextButton(onClick = { /*TODO*/ }) { Text("Inicio") }
                    TextButton(onClick = { /*TODO*/ }) { Text("Productos") }
                }
            }
        }
    ) { paddingValues ->
        // Usamos LazyColumn para que sea scrollable cuando aparece el teclado
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text("Registrar Producto", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = productName,
                    onValueChange = { productName = it },
                    label = { Text("Nombre producto") },
                    placeholder = { Text("Ingresa el nombre del producto") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Precio") },
                    placeholder = { Text("Ingresa el precio") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Dropdown para Categoría
                Box {
                    OutlinedTextField(
                        value = selectedCategory,
                        onValueChange = { },
                        label = { Text("Categoría") },
                        readOnly = true,
                        trailingIcon = {
                            Icon(
                                Icons.Default.ArrowDropDown,
                                "Dropdown",
                                Modifier.clickable { categoryExpanded = true })
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    DropdownMenu(
                        expanded = categoryExpanded,
                        onDismissRequest = { categoryExpanded = false }
                    ) {
                        categories.forEach { category ->
                            DropdownMenuItem(
                                text = { Text(category) },
                                onClick = {
                                    selectedCategory = category
                                    categoryExpanded = false
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Botón para subir imágenes
                Text("Subir imágenes", style = MaterialTheme.typography.bodyLarge)
                OutlinedButton(
                    onClick = { /* TODO: Lógica para seleccionar archivos */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Seleccionar archivos")
                }
                Spacer(modifier = Modifier.height(16.dp))


                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descripción") },
                    placeholder = { Text("Ingresa la descripción") },
                    modifier = Modifier.fillMaxWidth().height(120.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { /* TODO: Lógica para publicar producto */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Publicar Producto")
                }
                Spacer(modifier = Modifier.height(24.dp))

                // Placeholder para la imagen subida
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .border(1.dp, Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Imagen subida", color = Color.Gray)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RegisterProductScreenPreview() {
    PerfulandiaTheme {
        RegisterProductScreen()
    }
}