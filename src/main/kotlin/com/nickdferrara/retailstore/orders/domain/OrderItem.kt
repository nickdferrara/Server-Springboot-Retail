package com.nickdferrara.retailstore.orders.domain

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "order_items")
data class OrderItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val brand: String,
    val quantity: Int,
    val price: BigDecimal
)
