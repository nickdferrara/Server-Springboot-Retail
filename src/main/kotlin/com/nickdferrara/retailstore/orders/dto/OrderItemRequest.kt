package com.nickdferrara.retailstore.orders.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal

data class OrderItemRequest(
    @field:NotBlank
    val name: String,

    @field:NotBlank
    val brand: String,

    @field:NotNull
    @field:Positive
    val quantity: Int,

    @field:NotNull
    @field:Positive
    val price: BigDecimal
)
