package com.example.perfulandia.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
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

@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(
        topBar = { PerfulandiaTopBar() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                SectionTitle(title = "Nosotros")
                InfoCard(text = "Información sobre la empresa Perfulandia.")
                Spacer(modifier = Modifier.height(24.dp))

                SectionTitle(title = "Equipo")
                TeamMember(name = "Carlos Bletran", role = "Diseñador")
                TeamMember(name = "Susana Alvarez", role = "Desarrolladora")
                TeamMember(name = "Francisco Rodriguez", role = "Jefe de Proyecto")
                Spacer(modifier = Modifier.height(24.dp))

                SectionTitle(title = "Nuestras oficinas")
                InfoCard(text = "Puerto Montt\nJuan Soler Manfredini 200")
                Spacer(modifier = Modifier.height(24.dp))

                SectionTitle(title = "Contáctanos aquí")
                InfoCard(text = "Email: contacto@perfulandia.cl\nTeléfono: +569 1234 5678")
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 16.dp)
    )
}

@Composable
fun InfoCard(text: String) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun TeamMember(name: String, role: String) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = name, fontWeight = FontWeight.Bold)
            Text(text = role)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen()
}