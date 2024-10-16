package com.nickdferrara.retailstore.shipping.domain

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "shippings")
data class Shipping(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val orderId: Long,
    val shippingDate: LocalDate,
    val carrier: String,
    val trackingNumber: String
)
