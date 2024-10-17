package com.nickdferrara.retailstore.orders

import com.nickdferrara.retailstore.orders.domain.Order
import com.nickdferrara.retailstore.orders.domain.OrderItem
import com.nickdferrara.retailstore.orders.domain.OrderStatus
import com.nickdferrara.retailstore.orders.repository.OrderRepository
import com.nickdferrara.retailstore.orders.service.OrderService
import com.nickdferrara.retailstore.orders.service.OrderItemService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@SpringBootTest
class OrderServiceTests {

    private val orderRepository: OrderRepository = mock(OrderRepository::class.java)
    private val orderItemService: OrderItemService = mock(OrderItemService::class.java)
    private val orderService: OrderService = OrderService(orderRepository, orderItemService)

    @Test
    fun `test findAllOrders`() {
        val orders = listOf(
            Order(1, "ORD001", LocalDate.now(), OrderStatus.PENDING, BigDecimal(100), emptyList()),
            Order(2, "ORD002", LocalDate.now(), OrderStatus.SHIPPED, BigDecimal(200), emptyList())
        )
        `when`(orderRepository.findAll()).thenReturn(orders)

        val result = orderService.findAllOrders()
        assertEquals(2, result.size)
        assertEquals("ORD001", result[0].orderNumber)
        assertEquals("ORD002", result[1].orderNumber)
    }

    @Test
    fun `test findOrderById`() {
        val order = Order(1, "ORD001", LocalDate.now(), OrderStatus.PENDING, BigDecimal(100), emptyList())
        `when`(orderRepository.findById(1)).thenReturn(Optional.of(order))

        val result = orderService.findOrderById(1)
        assertNotNull(result)
        assertEquals("ORD001", result?.orderNumber)
    }

    @Test
    fun `test createOrder`() {
        val orderItems = listOf(
            OrderItem(1, "Item1", "Brand1", 1, BigDecimal(10)),
            OrderItem(2, "Item2", "Brand2", 2, BigDecimal(20))
        )
        val order = Order(1, "ORD001", LocalDate.now(), OrderStatus.PENDING, BigDecimal(100), orderItems)
        `when`(orderRepository.save(order)).thenReturn(order)

        val result = orderService.createOrder(order)
        assertNotNull(result)
        assertEquals("ORD001", result.orderNumber)
        assertEquals(OrderStatus.PENDING, result.status)
        verify(orderItemService, times(2)).createOrderItem(any(OrderItem::class.java))
    }

    @Test
    fun `test updateOrder`() {
        val orderItems = listOf(
            OrderItem(1, "Item1", "Brand1", 1, BigDecimal(10)),
            OrderItem(2, "Item2", "Brand2", 2, BigDecimal(20))
        )
        val order = Order(1, "ORD001", LocalDate.now(), OrderStatus.PENDING, BigDecimal(100), orderItems)
        `when`(orderRepository.existsById(1)).thenReturn(true)
        `when`(orderRepository.save(order)).thenReturn(order)

        val result = orderService.updateOrder(1, order)
        assertNotNull(result)
        assertEquals("ORD001", result.orderNumber)
        verify(orderItemService, times(2)).updateOrderItem(anyLong(), any(OrderItem::class.java))
    }

    @Test
    fun `test deleteOrder`() {
        `when`(orderRepository.existsById(1)).thenReturn(true)
        doNothing().`when`(orderRepository).deleteById(1)

        orderService.deleteOrder(1)
        verify(orderRepository, times(1)).deleteById(1)
    }

    @Test
    fun `test createOrder sets status to PENDING`() {
        val orderItems = listOf(
            OrderItem(1, "Item1", "Brand1", 1, BigDecimal(10)),
            OrderItem(2, "Item2", "Brand2", 2, BigDecimal(20))
        )
        val order = Order(1, "ORD001", LocalDate.now(), OrderStatus.PENDING, BigDecimal(100), orderItems)
        `when`(orderRepository.save(order)).thenReturn(order)

        val result = orderService.createOrder(order)
        assertNotNull(result)
        assertEquals(OrderStatus.PENDING, result.status)
    }
}
