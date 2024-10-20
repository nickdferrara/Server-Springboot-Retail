package com.nickdferrara.retailstore.orders.extensions

import com.nickdferrara.retailstore.orders.domain.OrderItem
import com.nickdferrara.retailstore.orders.dto.OrderItemRequest

fun OrderItemRequest.toOrderItem(): OrderItem {
    return OrderItem(
        brand = brand,
        name = name,
        quantity = quantity,
        price = price
    )
}