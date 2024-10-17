package com.nickdferrara.retailstore.orders.mapper

import com.nickdferrara.retailstore.orders.domain.OrderItem
import com.nickdferrara.retailstore.orders.dto.*
import org.mapstruct.*

@Mapper(componentModel = "spring")
interface CustomerInformationMapper {
    @Mapping(target = "id", ignore = true)
    fun toCustomerInformation(customerInformationRequest: CustomerInformationRequest): OrderItem
}