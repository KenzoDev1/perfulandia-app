package com.example.perfulandia.ui.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.perfulandia.ui.theme.PerfulandiaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterUserScreen() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var userTypeExpanded by remember { mutableStateOf(false) }
    val userTypes = listOf("Admin", "Cliente", "Vendedor")
    var selectedUserType by remember { mutableStateOf(userTypes[0]) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Perfulandia - Admin") },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Open drawer */ }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Registrar Usuario", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                placeholder = { Text("Ingresa el nombre del producto")},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico") },
                placeholder = { Text("Ingresa el correo electrónico")},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Dropdown para tipo de usuario
            Box {
                OutlinedTextField(
                    value = selectedUserType,
                    onValueChange = { },
                    label = { Text("Tipo de usuario") },
                    readOnly = true,
                    trailingIcon = {
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown",
                            Modifier.clickable { userTypeExpanded = true }
                        )
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

            Button(
                onClick = { /* TODO: Logic to create user */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Crear Usuario")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterUserScreenPreview() {
    PerfulandiaTheme {
        RegisterUserScreen()
    }
}
