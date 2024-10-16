package com.nickdferrara.retailstore.store.extension.store

import com.nickdferrara.retailstore.store.dto.AddressResponse
import com.nickdferrara.retailstore.store.domain.Address

fun Address.ToAddressResponse() : AddressResponse {
    return AddressResponse(
        id = this.id,
        street = this.street,
        city = this.city,
        state = this.state,
        zip = this.zip
    )
}