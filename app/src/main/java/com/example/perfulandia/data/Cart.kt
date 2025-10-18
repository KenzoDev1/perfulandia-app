package com.example.perfulandia.data

data class Cart (
    val id: Long,
    val userId: Long,
    val items: List<CartItem>
)