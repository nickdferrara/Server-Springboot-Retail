package com.nickdferrara.retailstore.store.dto

import org.springframework.modulith.NamedInterface
import org.springframework.modulith.PackageInfo
import java.time.LocalTime


class HoursOfOperationResponse(
    val id: Long,
    val dayOfWeek: String,
    val openTime: LocalTime,
    val closeTime: LocalTime
)