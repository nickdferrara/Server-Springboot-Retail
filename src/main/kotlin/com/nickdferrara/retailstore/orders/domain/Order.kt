package com.nickdferrara.retailstore.orders.domain

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "orders")
data class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val orderNumber: String,
    val orderDate: LocalDate,
    val status: String,
    val totalAmount: BigDecimal
)
