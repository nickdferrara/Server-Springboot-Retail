package com.nickdferrara.retailstore.fulfillment.domain

import com.nickdferrara.retailstore.orders.domain.CustomerInformation
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "pick_lists")
data class PickList(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val orderId: Long,
    @Enumerated(EnumType.STRING)
    val status: PickListStatus,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "pick_list_id")
    val pickListItems: List<PickListItem>,

    @OneToOne
    @JoinColumn(name = "pick_list_customer_id")
    val pickListCustomer: PickListCustomer
)

enum class PickListStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED
}
