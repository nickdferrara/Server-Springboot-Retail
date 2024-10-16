package com.nickdferrara.retailstore.pricing.repository

import com.nickdferrara.retailstore.pricing.domain.Pricing
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PricingRepository : JpaRepository<Pricing, Long> {
    fun findBySkuAndState(sku: String, state: String): Pricing?
}
