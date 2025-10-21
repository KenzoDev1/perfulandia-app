package com.example.perfulandia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.perfulandia.ui.MainScaffold // <-- IMPORTA EL NUEVO ARCHIVO
import com.example.perfulandia.ui.theme.PerfulandiaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PerfulandiaTheme {
                // Aquí llamamos a nuestro nuevo contenedor principal
                MainScaffold()
            }
        }
    }
}