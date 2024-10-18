package com.nickdferrara.retailstore.shipping.domain

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "shipping_orders")
class ShippingOrder(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val brand: String,
    val quantityRequested: Int,
    val quantityPicked: Int,
    val price: BigDecimal,

    @OneToOne
    @JoinColumn(name = "shipping_customer_id")
    val shippingCustomer: ShippingCustomer
)