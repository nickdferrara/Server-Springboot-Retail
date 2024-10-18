package com.nickdferrara.retailstore.shipping.domain

import jakarta.persistence.*

@Entity
@Table(name = "shipping_customer")
data class ShippingCustomer (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val storeNumber: String,
    val street: String,
    val city: String,
    val state: String,
    val zip: String
)