package com.nickdferrara.retailstore.orders.events

import com.nickdferrara.retailstore.orders.domain.Order

data class OrderReleasedEvent(
    val orderId: Long,
    val orderDetails: Order
)
