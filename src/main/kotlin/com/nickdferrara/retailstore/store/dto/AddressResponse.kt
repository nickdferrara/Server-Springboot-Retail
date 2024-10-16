package com.nickdferrara.retailstore.store.dto

import org.springframework.modulith.NamedInterface
import org.springframework.modulith.PackageInfo


class AddressResponse(
    val id: Long,
    val street: String,
    val city: String,
    val state: String,
    val zip: String
)
