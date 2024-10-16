package com.nickdferrara.retailstore.orders.service

import com.nickdferrara.retailstore.orders.domain.Order
import com.nickdferrara.retailstore.orders.domain.OrderItem
import com.nickdferrara.retailstore.orders.dto.OrderRequest
import com.nickdferrara.retailstore.orders.repository.OrderRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val orderItemService: OrderItemService // Inject OrderItemService
) {

    fun findAllOrders(): List<Order> = orderRepository.findAll()

    fun findOrderById(id: Long): Order? = orderRepository.findById(id).orElse(null)

    fun createOrder(order: Order): Order {
        order.orderItems.forEach { orderItemService.createOrderItem(it) } // Use OrderItemService to save order items
        return orderRepository.save(order)
    }

    fun updateOrder(id: Long, order: Order): Order {
        return if (orderRepository.existsById(id)) {
            order.orderItems.forEach { orderItemService.updateOrderItem(it.id, it) } // Use OrderItemService to save order items
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

    fun convertToOrder(orderRequest: OrderRequest): Order {
        val orderItems = orderRequest.orderItems.map { 
            OrderItem(
                name = it.name,
                brand = it.brand,
                quantity = it.quantity,
                price = it.price
            )
        }
        return Order(
            orderNumber = orderRequest.orderNumber,
            orderDate = orderRequest.orderDate,
            status = orderRequest.status,
            totalAmount = orderRequest.totalAmount,
            orderItems = orderItems
        )
    }
}
