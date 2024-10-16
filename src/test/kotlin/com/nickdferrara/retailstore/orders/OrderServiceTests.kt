package com.nickdferrara.retailstore.orders

import com.nickdferrara.retailstore.orders.domain.Order
import com.nickdferrara.retailstore.orders.repository.OrderRepository
import com.nickdferrara.retailstore.orders.service.OrderService
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
    private val orderService: OrderService = OrderService(orderRepository)

    @Test
    fun `test findAllOrders`() {
        val orders = listOf(
            Order(1, "ORD001", LocalDate.now(), "NEW", BigDecimal(100)),
            Order(2, "ORD002", LocalDate.now(), "SHIPPED", BigDecimal(200))
        )
        `when`(orderRepository.findAll()).thenReturn(orders)

        val result = orderService.findAllOrders()
        assertEquals(2, result.size)
        assertEquals("ORD001", result[0].orderNumber)
        assertEquals("ORD002", result[1].orderNumber)
    }

    @Test
    fun `test findOrderById`() {
        val order = Order(1, "ORD001", LocalDate.now(), "NEW", BigDecimal(100))
        `when`(orderRepository.findById(1)).thenReturn(Optional.of(order))

        val result = orderService.findOrderById(1)
        assertNotNull(result)
        assertEquals("ORD001", result?.orderNumber)
    }

    @Test
    fun `test createOrder`() {
        val order = Order(1, "ORD001", LocalDate.now(), "NEW", BigDecimal(100))
        `when`(orderRepository.save(order)).thenReturn(order)

        val result = orderService.createOrder(order)
        assertNotNull(result)
        assertEquals("ORD001", result.orderNumber)
    }

    @Test
    fun `test updateOrder`() {
        val order = Order(1, "ORD001", LocalDate.now(), "NEW", BigDecimal(100))
        `when`(orderRepository.existsById(1)).thenReturn(true)
        `when`(orderRepository.save(order)).thenReturn(order)

        val result = orderService.updateOrder(1, order)
        assertNotNull(result)
        assertEquals("ORD001", result.orderNumber)
    }

    @Test
    fun `test deleteOrder`() {
        `when`(orderRepository.existsById(1)).thenReturn(true)
        doNothing().`when`(orderRepository).deleteById(1)

        orderService.deleteOrder(1)
        verify(orderRepository, times(1)).deleteById(1)
    }
}
