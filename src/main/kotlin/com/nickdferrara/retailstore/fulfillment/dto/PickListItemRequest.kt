package com.nickdferrara.retailstore.fulfillment.dto

import java.math.BigDecimal

data class PickListItemRequest(
    val name: String,
    val brand: String,
    val quantity: Int,
    val pickedQuantity: Int,
    val price: BigDecimal
)
