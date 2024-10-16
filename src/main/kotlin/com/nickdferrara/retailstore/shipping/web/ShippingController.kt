package com.nickdferrara.retailstore.shipping.web

import com.nickdferrara.retailstore.shipping.domain.Shipping
import com.nickdferrara.retailstore.shipping.service.ShippingService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/shippings")
class ShippingController(private val shippingService: ShippingService) {

    @GetMapping
    fun getAllShippings(): List<Shipping> = shippingService.findAllShippings()

    @GetMapping("/{id}")
    fun getShippingById(@PathVariable id: Long): ResponseEntity<Shipping> {
        val shipping = shippingService.findShippingById(id)
        return if (shipping != null) {
            ResponseEntity.ok(shipping)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createShipping(@RequestBody shipping: Shipping): Shipping = shippingService.createShipping(shipping)

    @PutMapping("/{id}")
    fun updateShipping(@PathVariable id: Long, @RequestBody shipping: Shipping): ResponseEntity<Shipping> {
        return try {
            ResponseEntity.ok(shippingService.updateShipping(id, shipping))
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteShipping(@PathVariable id: Long): ResponseEntity<Unit> {
        return try {
            shippingService.deleteShipping(id)
            ResponseEntity.noContent().build()
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }
}
