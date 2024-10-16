package com.nickdferrara.retailstore.store.extension.store

import com.nickdferrara.retailstore.store.dto.StoreResponse
import com.nickdferrara.retailstore.store.domain.Store

fun Store.ToStoreResponse(): StoreResponse {
    return StoreResponse(
        id = this.id,
        storeNumber = this.storeNumber,
        address = this.address.ToAddressResponse(),
        operatingHours = this.hoursOfOperation.map { it.ToHoursOfOperationResponse() }
    )
}