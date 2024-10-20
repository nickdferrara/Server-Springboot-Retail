package com.nickdferrara.retailstore.orders.domain

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "orders")
data class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val orderNumber: String,
    val orderDate: LocalDate,
    @Enumerated(EnumType.STRING)
    val status: OrderStatus,
    val totalAmount: BigDecimal,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "order_id")
    val orderItems: List<OrderItem>,

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "customer_information_id")
    val customerInformation: CustomerInformation
)

enum class OrderStatus {
    PENDING,
    CONFIRMED,
    RELEASED,
    FULFILLED,
    SHIPPED,
    RETURNED,
    COMPLETED
}
