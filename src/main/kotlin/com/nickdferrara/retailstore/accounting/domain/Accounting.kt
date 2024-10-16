package com.nickdferrara.retailstore.accounting.domain

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "accountings")
data class Accounting(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val orderId: Long,
    val accountingDate: LocalDate,
    val amount: BigDecimal
)
