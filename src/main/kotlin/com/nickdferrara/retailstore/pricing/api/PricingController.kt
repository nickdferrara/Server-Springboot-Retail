package com.nickdferrara.retailstore.pricing.api

import com.nickdferrara.retailstore.pricing.domain.Pricing
import com.nickdferrara.retailstore.pricing.service.PricingService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pricings")
class PricingController(private val pricingService: PricingService) {

    @GetMapping
    fun getAllPricings(): List<Pricing> = pricingService.findAllPricing()

    @GetMapping("/{id}")
    fun getPricingById(@PathVariable id: Long): ResponseEntity<Pricing> {
        val pricing = pricingService.findPricingById(id)
        return if (pricing != null) {
            ResponseEntity.ok(pricing)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPricing(@RequestBody pricing: Pricing): Pricing = pricingService.createPricing(pricing)

    @PutMapping("/{id}")
    fun updatePricing(@PathVariable id: Long, @RequestBody pricing: Pricing): ResponseEntity<Pricing> {
        return try {
            ResponseEntity.ok(pricingService.updatePricing(id, pricing))
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deletePricing(@PathVariable id: Long): ResponseEntity<Unit> {
        return try {
            pricingService.deletePricing(id)
            ResponseEntity.noContent().build()
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }
}
