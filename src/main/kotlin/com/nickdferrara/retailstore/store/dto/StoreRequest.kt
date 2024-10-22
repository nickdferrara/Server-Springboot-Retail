package com.nickdferrara.retailstore.store.dto

import com.nickdferrara.retailstore.store.domain.Address
import com.nickdferrara.retailstore.store.domain.HoursOfOperation

data class StoreRequest(
    val storeNumber: String,
    val address: Address,
    val hoursOfOperation: List<HoursOfOperation>
)
