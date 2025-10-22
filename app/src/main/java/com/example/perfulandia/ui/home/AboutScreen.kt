package com.example.perfulandia.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            SectionTitle(title = "Nosotros")
            InfoCard(text = "Información sobre la empresa Perfulandia.")
            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle(title = "Equipo")
            TeamMember(name = "Carlos Bittner", role = "Desarrollador")
            TeamMember(name = "Vicente Alarcon", role = "Diseñador")
            TeamMember(name = "Francisco Aranguiz", role = "Desarrollador")
            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle(title = "Nuestras oficinas")
            InfoCard(text = "Puerto Montt\nAvenida Austral")
            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle(title = "Contáctanos aquí")
            InfoCard(text = "Email: contacto@perfulandia.cl\nTeléfono: +569 1234 5678")
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