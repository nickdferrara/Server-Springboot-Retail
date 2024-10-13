package com.nickdferrara.retailstore.store.domain

import jakarta.persistence.*

@Entity
@Table(name = "addresses")
data class Address(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val street: String,
    val city: String,
    val state: String,
    val zip: String
)
