package com.nickdferrara.retailstore.pricing.domain

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "pricings")
data class Pricing(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val retailPrice: BigDecimal,
    val inventoryPrice: BigDecimal,
    val storePrice: BigDecimal
)
