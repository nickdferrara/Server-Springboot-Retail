package com.nickdferrara.retailstore.orders.dto

import com.nickdferrara.retailstore.orders.domain.OrderStatus
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal
import java.time.LocalDate

data class OrderRequest(
    @field:NotBlank
    val orderNumber: String,

    @field:NotNull
    val orderDate: LocalDate,

    @field:NotNull
    val status: OrderStatus,

    @field:NotNull
    @field:Positive
    val totalAmount: BigDecimal,

    @field:NotNull
    val orderItems: List<OrderItemRequest>,

    @field:NotNull
    val customerInformation: CustomerInformationRequest
)
