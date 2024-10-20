package com.nickdferrara.retailstore.orders.extensions

import com.nickdferrara.retailstore.orders.domain.CustomerInformation
import com.nickdferrara.retailstore.orders.dto.CustomerInformationRequest

fun CustomerInformationRequest.toCustomerInformation(): CustomerInformation {
    return CustomerInformation(
        storeNumber = storeNumber,
        street = street,
        city = city,
        state = state,
        zip = zip
    )
}