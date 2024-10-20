package com.nickdferrara.retailstore.orders.service

import com.nickdferrara.retailstore.orders.domain.Order
import com.nickdferrara.retailstore.orders.domain.OrderStatus
import com.nickdferrara.retailstore.orders.events.OrderReleasedEvent
import com.nickdferrara.retailstore.orders.repository.OrderRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val orderItemService: OrderItemService,
    private val customerInformationService: CustomerInformationService,
    private val eventPublisher: ApplicationEventPublisher
) {

    fun findAllOrders(): List<Order> = orderRepository.findAll()

    fun findOrderById(id: Long): Order? = orderRepository.findById(id).orElse(null)

    fun createOrder(order: Order): Order {
        order.orderItems.forEach { orderItemService.createOrderItem(it) }
            .also { customerInformationService.createCustomerInformation(order.customerInformation) }
        val updatedOrder = order.copy(status = OrderStatus.PENDING, orderNumber = UUID.randomUUID().toString())
        return orderRepository.save(updatedOrder)
    }

    fun updateOrder(order: Order): Order {

        val existingOrder = orderRepository.findByOrderNumber(order.orderNumber)
        if (existingOrder != null) {
            order.orderItems.forEach { orderItemService.createOrderItem(it) }
            customerInformationService.createCustomerInformation(order.customerInformation)

            val updatedOrder = order.copy(id = existingOrder.id, status = order.status)
            val savedOrder = orderRepository.save(updatedOrder)

            if (savedOrder.status == OrderStatus.RELEASED) {
                eventPublisher.publishEvent(OrderReleasedEvent(savedOrder.id, savedOrder))
            }
            return savedOrder
        } else {
            throw NoSuchElementException("Order number ${order.orderNumber} not found")
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
