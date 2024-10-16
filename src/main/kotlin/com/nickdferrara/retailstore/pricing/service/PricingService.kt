package com.nickdferrara.retailstore.pricing.service

import com.nickdferrara.retailstore.pricing.domain.Pricing
import com.nickdferrara.retailstore.pricing.repository.PricingRepository
import com.nickdferrara.retailstore.store.StoreApi
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate

@Service
class PricingService(
    private val pricingRepository: PricingRepository,
    val storeApi: StoreApi
) {

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

    fun findInventoryValue(sku: String, storeNumber: String, effectiveDate: LocalDate): BigDecimal {
        val store = storeApi.findStoreByNumber(storeNumber)
            ?: throw NoSuchElementException("Store not found with number $storeNumber")

        val pricing = pricingRepository.findBySkuAndState(sku, store.address.state)
            ?: throw NoSuchElementException("Pricing not found for SKU $sku in state ${store.address.state}")

        return 0.0.toBigDecimal()
    }
}
