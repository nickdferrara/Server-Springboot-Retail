package com.nickdferrara.retailstore.fulfillment.listener

import com.nickdferrara.retailstore.fulfillment.service.PickListService
import com.nickdferrara.retailstore.orders.events.OrderReleasedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class OrderReleasedListener(
    private val pickListService: PickListService
) {

    @EventListener
    fun handleOrderReleasedEvent(event: OrderReleasedEvent) {
        pickListService.createPickListFromOrder(event.orderDetails)
    }
}
