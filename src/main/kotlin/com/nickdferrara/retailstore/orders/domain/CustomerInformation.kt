package com.nickdferrara.retailstore.orders.domain

import jakarta.persistence.*

@Entity
@Table(name = "customer_information")
data class CustomerInformation (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val storeNumber: String,
    val street: String,
    val city: String,
    val state: String,
    val zip: String
)