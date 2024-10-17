package com.nickdferrara.retailstore.shipping.listener

import com.nickdferrara.retailstore.fulfillment.events.PickListCompleteEvent
import com.nickdferrara.retailstore.shipping.service.ShippingRouteService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class PickListCompleteListener(
    private val shippingRouteService: ShippingRouteService
) {

    @EventListener
    fun handlePickListCompleteEvent(event: PickListCompleteEvent) {
        // Implement the method to handle the event and perform necessary actions
        // For example, you can call a method from ShippingRouteService to handle the event
        shippingRouteService.handlePickListComplete(event.pickListId, event.pickList)
    }
}
