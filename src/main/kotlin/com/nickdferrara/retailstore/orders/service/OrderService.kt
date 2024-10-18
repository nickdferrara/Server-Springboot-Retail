package com.nickdferrara.retailstore.orders.service

import com.nickdferrara.retailstore.orders.domain.Order
import com.nickdferrara.retailstore.orders.domain.OrderItem
import com.nickdferrara.retailstore.orders.domain.OrderStatus
import com.nickdferrara.retailstore.orders.dto.OrderRequest
import com.nickdferrara.retailstore.orders.mapper.OrderMapper
import com.nickdferrara.retailstore.orders.repository.OrderRepository
import com.nickdferrara.retailstore.orders.events.OrderReleasedEvent
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
        val updatedOrder = order.copy(status = OrderStatus.PENDING)
        return orderRepository.save(updatedOrder)
    }

    fun updateOrder(id: Long, order: Order): Order {
        if (orderRepository.existsById(id)) {
            order.orderItems.forEach { orderItemService.updateOrderItem(it.id, it) }
            val updatedOrder = orderRepository.save(order.copy(id = id))
            if (updatedOrder.status == OrderStatus.RELEASED) {
                eventPublisher.publishEvent(OrderReleasedEvent(updatedOrder.id, updatedOrder))
            }
            return updatedOrder
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
