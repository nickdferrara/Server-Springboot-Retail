package com.nickdferrara.retailstore.fulfillment.domain

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "pick_list_items")
data class PickListItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val brand: String,
    val quantity: Int,
    val pickedQuantity: Int,
    val price: BigDecimal
)
