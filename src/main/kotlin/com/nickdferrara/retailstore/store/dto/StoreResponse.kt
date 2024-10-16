package com.nickdferrara.retailstore.store.dto

import org.springframework.modulith.NamedInterface
import org.springframework.modulith.PackageInfo

@PackageInfo
@NamedInterface
class StoreResponse(
    val id: Long,
    val storeNumber: String,
    val address: AddressResponse,
    val operatingHours : List<HoursOfOperationResponse>
)