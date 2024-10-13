package com.nickdferrara.retailstore.pricing.service

import com.nickdferrara.retailstore.pricing.domain.Pricing
import com.nickdferrara.retailstore.pricing.repository.PricingRepository
import org.springframework.stereotype.Service

@Service
class PricingService(private val pricingRepository: PricingRepository) {

    fun findAllPricing(): List<Pricing> = pricingRepository.findAll()

    fun findPricingById(id: Long): Pricing? = pricingRepository.findById(id).orElse(null)

    fun createPricing(pricing: Pricing): Pricing = pricingRepository.save(pricing)

    fun updatePricing(id: Long, pricing: Pricing): Pricing {
        return if (pricingRepository.existsById(id)) {
            pricingRepository.save(pricing.copy(id = id))
        } else {
            throw NoSuchElementException("Pricing with id $id not found")
        }
    }

    fun deletePricing(id: Long) {
        if (pricingRepository.existsById(id)) {
            pricingRepository.deleteById(id)
        } else {
            throw NoSuchElementException("Pricing with id $id not found")
        }
    }
}
