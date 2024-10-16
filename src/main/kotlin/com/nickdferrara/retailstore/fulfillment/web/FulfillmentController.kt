package com.nickdferrara.retailstore.fulfillment.web

import com.nickdferrara.retailstore.fulfillment.domain.Fulfillment
import com.nickdferrara.retailstore.fulfillment.service.FulfillmentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/fulfillments")
class FulfillmentController(private val fulfillmentService: FulfillmentService) {

    @GetMapping
    fun getAllFulfillments(): List<Fulfillment> = fulfillmentService.findAllFulfillments()

    @GetMapping("/{id}")
    fun getFulfillmentById(@PathVariable id: Long): ResponseEntity<Fulfillment> {
        val fulfillment = fulfillmentService.findFulfillmentById(id)
        return if (fulfillment != null) {
            ResponseEntity.ok(fulfillment)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createFulfillment(@RequestBody fulfillment: Fulfillment): Fulfillment = fulfillmentService.createFulfillment(fulfillment)

    @PutMapping("/{id}")
    fun updateFulfillment(@PathVariable id: Long, @RequestBody fulfillment: Fulfillment): ResponseEntity<Fulfillment> {
        return try {
            ResponseEntity.ok(fulfillmentService.updateFulfillment(id, fulfillment))
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteFulfillment(@PathVariable id: Long): ResponseEntity<Unit> {
        return try {
            fulfillmentService.deleteFulfillment(id)
            ResponseEntity.noContent().build()
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }
}
