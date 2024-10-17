package com.nickdferrara.retailstore.fulfillment.mapper

import com.nickdferrara.retailstore.fulfillment.domain.PickList
import com.nickdferrara.retailstore.orders.domain.Order
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring", uses = [PickListItemMapper::class])
interface PickListMapper {

    @Mapping(target = "id", ignore = true)
    fun toPickList(order: Order): PickList
}
