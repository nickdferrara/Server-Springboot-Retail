package com.nickdferrara.retailstore.orders.dto

data class StoreRequest(
    val id: Long,
    val storeNumber: String,
    val address: AddressRequest,
    val hoursOfOperation: List<HoursOfOperationRequest>
)
