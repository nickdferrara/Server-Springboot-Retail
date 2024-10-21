package com.nickdferrara.retailstore.shipping.mapper

import com.nickdferrara.retailstore.fulfillment.domain.PickList
import com.nickdferrara.retailstore.shipping.domain.ShippingRoute
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring", uses = [ShippingOrdersMapper::class, ShippingCustomerMapper::class])
interface ShippingRouteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", expression = "java(com.nickdferrara.retailstore.shipping.domain.ShippingStatus.STAGING)")
    @Mapping(target = "shippingDate", expression = "java(java.time.LocalDate.now())")
    @Mapping(source = "pickListItems", target = "shippingOrders")
    @Mapping(source = "pickListCustomer", target = "shippingCustomer")
    fun toShippingRoute(pickList: PickList): ShippingRoute
}