package com.nickdferrara.retailstore.shipping.listener

import com.nickdferrara.retailstore.fulfillment.events.PickListCompleteEvent
import com.nickdferrara.retailstore.shipping.mapper.ShippingRouteMapper
import com.nickdferrara.retailstore.shipping.service.ShippingRouteService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class PickListCompleteListener(
    private val shippingRouteService: ShippingRouteService,
    private val shippingRouteMapper: ShippingRouteMapper

) {

    @EventListener
    fun handlePickListCompleteEvent(event: PickListCompleteEvent) {
        val shippingRoute = shippingRouteMapper.toShippingRoute(event.pickList)
        shippingRouteService.createShippingRoute(shippingRoute)
    }
}
