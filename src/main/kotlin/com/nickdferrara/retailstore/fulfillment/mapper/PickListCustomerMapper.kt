package com.nickdferrara.retailstore.fulfillment.mapper

import com.nickdferrara.retailstore.fulfillment.domain.PickListCustomer
import com.nickdferrara.retailstore.fulfillment.dto.PickListCustomerRequest
import com.nickdferrara.retailstore.orders.domain.CustomerInformation
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface PickListCustomerMapper {

    @Mapping(target = "id", ignore = true)
    fun toPickListCustomer(customerInformation: CustomerInformation): PickListCustomer

    @Mapping(target = "id", ignore = true)
    fun fromDtoToPickListCustomer(pickListCustomerRequest: PickListCustomerRequest): PickListCustomer
}