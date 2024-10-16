package com.nickdferrara.retailstore.orders.service

import com.nickdferrara.retailstore.orders.domain.OrderItem
import com.nickdferrara.retailstore.orders.repository.OrderItemRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderItemService(private val orderItemRepository: OrderItemRepository) {

    fun findAllOrderItems(): List<OrderItem> = orderItemRepository.findAll()

    fun findOrderItemById(id: Long): OrderItem? = orderItemRepository.findById(id).orElse(null)

    fun createOrderItem(orderItem: OrderItem): OrderItem = orderItemRepository.save(orderItem)

    fun updateOrderItem(id: Long, orderItem: OrderItem): OrderItem {
        return if (orderItemRepository.existsById(id)) {
            orderItemRepository.save(orderItem.copy(id = id))
        } else {
            throw NoSuchElementException("OrderItem with id $id not found")
        }
    }

    fun deleteOrderItem(id: Long) {
        if (orderItemRepository.existsById(id)) {
            orderItemRepository.deleteById(id)
        } else {
            throw NoSuchElementException("OrderItem with id $id not found")
        }
    }
}
