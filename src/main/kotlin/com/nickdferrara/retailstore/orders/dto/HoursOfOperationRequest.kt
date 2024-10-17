package com.nickdferrara.retailstore.orders.dto

import java.time.DayOfWeek
import java.time.LocalTime

data class HoursOfOperationRequest(
    val dayOfWeek: DayOfWeek,
    val openingTime: LocalTime,
    val closingTime: LocalTime
)
