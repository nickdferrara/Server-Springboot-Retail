package com.nickdferrara.retailstore.store.extension.store

import com.nickdferrara.retailstore.store.dto.HoursOfOperationResponse
import com.nickdferrara.retailstore.store.domain.HoursOfOperation

fun HoursOfOperation.ToHoursOfOperationResponse(): HoursOfOperationResponse {
    return HoursOfOperationResponse(
        id = this.id,
        dayOfWeek = this.dayOfWeek.name,
        openTime = this.openingTime,
        closeTime = this.closingTime
    )
}