package com.nickdferrara.retailstore.shipping.domain

import com.nickdferrara.retailstore.orders.domain.Order
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "shipping_routes")
data class ShippingRoute(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val orderId: Long,
    val shippingDate: LocalDate,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "shipping_route_id")
    val shippingOrders: List<Order>,

    @Enumerated(EnumType.STRING)
    val status: ShippingStatus
)

enum class ShippingStatus {
    STAGING,
    LOADING,
    COMPLETE,
    DELIVERED
}
