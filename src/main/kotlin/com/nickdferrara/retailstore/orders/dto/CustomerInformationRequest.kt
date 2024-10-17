package com.nickdferrara.retailstore.orders.dto

import jakarta.validation.constraints.NotBlank

class CustomerInformationRequest (
    @field:NotBlank
    val storeNumber: String,

    @field:NotBlank
    val street: String,

    @field:NotBlank
    val city: String,

    @field:NotBlank
    val state: String,

    @field:NotBlank
    val zip: String
)