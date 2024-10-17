package com.nickdferrara.retailstore.orders.web

import com.nickdferrara.retailstore.orders.domain.Order
import com.nickdferrara.retailstore.orders.dto.OrderRequest
import com.nickdferrara.retailstore.orders.service.OrderService
import com.nickdferrara.retailstore.orders.mapper.OrderMapper
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/orders")
@Validated
class OrderController(
    private val orderService: OrderService,
    private val orderMapper: OrderMapper // Inject OrderMapper
) {

    @GetMapping
    fun getAllOrders(): List<Order> = orderService.findAllOrders()

    @GetMapping("/{id}")
    fun getOrderById(@PathVariable id: Long): ResponseEntity<Order> {
        val order = orderService.findOrderById(id)
        return if (order != null) {
            ResponseEntity.ok(order)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrder(@Valid @RequestBody orderRequest: OrderRequest): Order {
        val order = orderMapper.toOrder(orderRequest)
        return orderService.createOrder(order)
    }

    @PutMapping("/{id}")
    fun updateOrder(@PathVariable id: Long, @Valid @RequestBody orderRequest: OrderRequest): ResponseEntity<Order> {
        return try {
            val order = orderMapper.toOrder(orderRequest)
            ResponseEntity.ok(orderService.updateOrder(id, order))
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteOrder(@PathVariable id: Long): ResponseEntity<Unit> {
        return try {
            orderService.deleteOrder(id)
            ResponseEntity.noContent().build()
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }
}
