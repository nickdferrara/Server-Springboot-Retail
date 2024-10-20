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

    fun updateOrderItem(orderItem: OrderItem): OrderItem {
        return if (orderItemRepository.existsById(orderItem.id)) {
            orderItemRepository.save(orderItem.copy(
                id = orderItem.id,
                brand = orderItem.brand,
                name = orderItem.name,
                quantity = orderItem.quantity,
                price = orderItem.price
            ))
        } else {
            throw NoSuchElementException("OrderItem with id ${orderItem.id} not found")
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
