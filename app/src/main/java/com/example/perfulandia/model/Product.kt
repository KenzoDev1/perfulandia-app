package com.example.perfulandia.model

data class Product (
    val id: Int,
    val name: String,
    val price: Double,
    val stock: Int,
    val imageUrl: String = ""
)