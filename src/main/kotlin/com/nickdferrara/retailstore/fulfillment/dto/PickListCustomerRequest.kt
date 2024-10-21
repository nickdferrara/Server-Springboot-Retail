package com.nickdferrara.retailstore.fulfillment.dto

data class PickListCustomerRequest(
    val storeNumber: String,
    val street: String,
    val city: String,
    val state: String,
    val zip: String
)
