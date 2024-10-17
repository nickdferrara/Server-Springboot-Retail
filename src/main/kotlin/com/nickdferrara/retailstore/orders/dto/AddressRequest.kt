package com.nickdferrara.retailstore.orders.dto

data class AddressRequest(
    val street: String,
    val city: String,
    val state: String,
    val zip: String
)
