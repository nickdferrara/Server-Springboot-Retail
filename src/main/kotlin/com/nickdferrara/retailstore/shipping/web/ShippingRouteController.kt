package com.nickdferrara.retailstore.shipping.web

import com.nickdferrara.retailstore.shipping.domain.ShippingRoute
import com.nickdferrara.retailstore.shipping.service.ShippingRouteService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/shippings")
class ShippingRouteController(private val shippingRouteService: ShippingRouteService) {

    @GetMapping
    fun getAllShippings(): List<ShippingRoute> = shippingRouteService.findAllShippings()

    @GetMapping("/{id}")
    fun getShippingById(@PathVariable id: Long): ResponseEntity<ShippingRoute> {
        val shippingRoute = shippingRouteService.findShippingById(id)
        return if (shippingRoute != null) {
            ResponseEntity.ok(shippingRoute)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createShipping(@RequestBody shippingRoute: ShippingRoute): ShippingRoute = shippingRouteService.createShipping(shippingRoute)

    @PutMapping("/{id}")
    fun updateShipping(@PathVariable id: Long, @RequestBody shippingRoute: ShippingRoute): ResponseEntity<ShippingRoute> {
        return try {
            ResponseEntity.ok(shippingRouteService.updateShipping(id, shippingRoute))
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteShipping(@PathVariable id: Long): ResponseEntity<Unit> {
        return try {
            shippingRouteService.deleteShipping(id)
            ResponseEntity.noContent().build()
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }
}
