package com.example.perfulandia.data

data class Product (
    val id: Int,
    val name: String,
    val price: Double,
    val stock: Int,
    val imageUrl: String = ""
)