package com.nickdferrara.retailstore.orders.repository

import com.nickdferrara.retailstore.orders.domain.OrderItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderItemRepository : JpaRepository<OrderItem, Long>
