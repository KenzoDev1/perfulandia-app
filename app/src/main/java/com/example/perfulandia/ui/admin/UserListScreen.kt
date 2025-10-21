package com.example.perfulandia.ui.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.perfulandia.model.User
import com.example.perfulandia.ui.theme.PerfulandiaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen() {
    // Datos de ejemplo
    val users = listOf(
        User(1, "Carlos Bittner", "carlos@email.com", "Admin"),
        User(2, "Vicente Alarcón", "vicente@email.com", "Cliente"),
        User(3, "Francisco Aranguiz", "francisco@email.com", "Vendedor"),
    )

    var searchQuery by remember { mutableStateOf("") }
    var userTypeExpanded by remember { mutableStateOf(false) }
    val userTypes = listOf("Todos", "Admin", "Cliente", "Vendedor")
    var selectedUserType by remember { mutableStateOf(userTypes[0]) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Perfulandia - Admin") },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Open drawer */ }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Person, contentDescription = "Profile")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextButton(onClick = { /*TODO*/ }) { Text("Inicio") }
                    TextButton(onClick = { /*TODO*/ }) { Text("Usuarios") }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            item {
                Text("Usuarios registrados", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))

                // Filtros
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Buscar por nombre") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box {
                    OutlinedTextField(
                        value = selectedUserType,
                        onValueChange = {},
                        label = { Text("Filtrar por Tipo de usuario:") },
                        readOnly = true,
                        trailingIcon = {
                            Icon(
                                Icons.Default.ArrowDropDown,
                                "Dropdown",
                                Modifier.clickable { userTypeExpanded = true })
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    DropdownMenu(
                        expanded = userTypeExpanded,
                        onDismissRequest = { userTypeExpanded = false }
                    ) {
                        userTypes.forEach { type ->
                            DropdownMenuItem(
                                text = { Text(type) },
                                onClick = {
                                    selectedUserType = type
                                    userTypeExpanded = false
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Encabezados de la tabla
            item {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Nombre", Modifier.weight(1f), fontWeight = FontWeight.Bold)
                    Text("Correo", Modifier.weight(1f), fontWeight = FontWeight.Bold)
                    Text("Rol", Modifier.weight(0.5f), fontWeight = FontWeight.Bold)
                }
                Divider()
            }

            // Lista de usuarios (simulando una tabla)
            items(users) { user ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(user.name, Modifier.weight(1f))
                    Text(user.email, Modifier.weight(1f))
                    Text(user.rol, Modifier.weight(0.5f))
                }
                Divider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserListScreenPreview() {
    PerfulandiaTheme {
        UserListScreen()
    }
}
