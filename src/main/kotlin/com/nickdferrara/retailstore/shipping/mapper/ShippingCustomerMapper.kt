package com.nickdferrara.retailstore.shipping.mapper

import com.nickdferrara.retailstore.fulfillment.domain.PickListCustomer
import com.nickdferrara.retailstore.shipping.domain.ShippingCustomer
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface ShippingCustomerMapper {

    @Mapping(target = "id", ignore = true)
    fun toShippingCustomer(pickListCustomer: PickListCustomer): ShippingCustomer
}