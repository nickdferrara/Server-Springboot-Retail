package com.nickdferrara.retailstore.shipping.mapper

import com.nickdferrara.retailstore.fulfillment.domain.PickList
import com.nickdferrara.retailstore.shipping.domain.ShippingRoute
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring", uses = [ShippingOrdersMapper::class])
interface ShippingRouteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "shippingDate", ignore = true)
    fun toShippingRoute(pickList: PickList): ShippingRoute
}