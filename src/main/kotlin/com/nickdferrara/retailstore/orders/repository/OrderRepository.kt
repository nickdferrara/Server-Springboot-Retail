package com.nickdferrara.retailstore.orders.repository

import com.nickdferrara.retailstore.orders.domain.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Long> {
    fun findByOrderNumber(orderNumber: String): Order?
}
