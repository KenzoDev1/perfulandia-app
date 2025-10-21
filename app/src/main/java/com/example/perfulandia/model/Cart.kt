package com.example.perfulandia.model

data class Cart (
    val id: Long,
    val userId: Long,
    val items: List<CartItem>
)