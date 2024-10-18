package com.nickdferrara.retailstore.fulfillment.domain

import jakarta.persistence.*

@Entity
@Table(name = "pick_list_customers")
data class PickListCustomer (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val storeNumber: String,
    val street: String,
    val city: String,
    val state: String,
    val zip: String
)