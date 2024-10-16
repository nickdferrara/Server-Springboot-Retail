package com.nickdferrara.retailstore.orders.service

import com.nickdferrara.retailstore.orders.domain.Order
import com.nickdferrara.retailstore.orders.repository.OrderRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService(private val orderRepository: OrderRepository) {

    fun findAllOrders(): List<Order> = orderRepository.findAll()

    fun findOrderById(id: Long): Order? = orderRepository.findById(id).orElse(null)

    fun createOrder(order: Order): Order {
        order.orderItems.forEach { it.order = order }
        return orderRepository.save(order)
    }

    fun updateOrder(id: Long, order: Order): Order {
        return if (orderRepository.existsById(id)) {
            order.orderItems.forEach { it.order = order }
            orderRepository.save(order.copy(id = id))
        } else {
            throw NoSuchElementException("Order with id $id not found")
        }
    }

    fun deleteOrder(id: Long) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id)
        } else {
            throw NoSuchElementException("Order with id $id not found")
        }
    }
}
