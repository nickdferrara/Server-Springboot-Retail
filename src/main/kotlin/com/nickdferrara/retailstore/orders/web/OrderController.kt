package com.nickdferrara.retailstore.orders.web

import com.nickdferrara.retailstore.orders.domain.Order
import com.nickdferrara.retailstore.orders.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/orders")
class OrderController(private val orderService: OrderService) {

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
    fun createOrder(@RequestBody order: Order): Order = orderService.createOrder(order)

    @PutMapping("/{id}")
    fun updateOrder(@PathVariable id: Long, @RequestBody order: Order): ResponseEntity<Order> {
        return try {
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