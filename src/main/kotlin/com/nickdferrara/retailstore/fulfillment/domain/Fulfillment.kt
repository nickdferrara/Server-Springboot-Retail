package com.nickdferrara.retailstore.fulfillment.domain

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "fulfillments")
data class Fulfillment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val orderId: Long,
    val fulfillmentDate: LocalDate,
    val status: String
)