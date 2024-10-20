package com.nickdferrara.retailstore.fulfillment.mapper

import com.nickdferrara.retailstore.fulfillment.domain.PickList
import com.nickdferrara.retailstore.orders.domain.Order
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring", uses = [PickListItemMapper::class, PickListCustomerMapper::class])
interface PickListMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", expression = "java(com.nickdferrara.retailstore.fulfillment.domain.PickListStatus.PENDING)")
    @Mapping(source = "orderItems", target = "pickListItems")
    @Mapping(source = "customerInformation", target = "pickListCustomer")
    fun toPickList(order: Order): PickList
}
