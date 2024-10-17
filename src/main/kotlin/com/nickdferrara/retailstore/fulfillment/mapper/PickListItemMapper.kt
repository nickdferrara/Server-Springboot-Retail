package com.nickdferrara.retailstore.fulfillment.mapper

import com.nickdferrara.retailstore.fulfillment.domain.PickListItem
import com.nickdferrara.retailstore.orders.domain.OrderItem
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface PickListItemMapper {

    @Mapping(target = "id", ignore = true)
    fun toPickListItem(orderItem: OrderItem): PickListItem
}
