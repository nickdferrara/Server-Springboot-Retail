package com.nickdferrara.retailstore.orders.extensions

import com.nickdferrara.retailstore.orders.domain.Order
import com.nickdferrara.retailstore.orders.domain.OrderStatus
import com.nickdferrara.retailstore.orders.dto.OrderRequest


fun OrderRequest.toNewOrder(): Order {
    return Order(
        orderNumber = "0",
        orderDate = orderDate,
        totalAmount = totalAmount,
        status = OrderStatus.PENDING,
        orderItems = orderItems.map { it.toOrderItem() },
        customerInformation = customerInformation.toCustomerInformation()
    )
}

fun OrderRequest.toOrder(): Order {
    return Order(
        orderNumber = orderNumber!!,
        orderDate = orderDate,
        totalAmount = totalAmount,
        status = status!!,
        orderItems = orderItems.map { it.toOrderItem() },
        customerInformation = customerInformation.toCustomerInformation()
    )
}