package com.nickdferrara.retailstore.store.domain

import jakarta.persistence.*

@Entity
@Table(name = "stores")
data class Store(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true)
    val storeNumber: String,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "address_id")
    val address: Address,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "store_id")
    val hoursOfOperation: List<HoursOfOperation>
)
