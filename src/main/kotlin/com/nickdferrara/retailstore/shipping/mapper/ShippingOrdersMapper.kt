package com.nickdferrara.retailstore.shipping.mapper

import com.nickdferrara.retailstore.fulfillment.domain.PickListItem
import com.nickdferrara.retailstore.shipping.domain.ShippingOrder
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface ShippingOrdersMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "quantity", target = "quantityPicked")
    fun toShippingOrder(pickListItem: PickListItem): ShippingOrder
}