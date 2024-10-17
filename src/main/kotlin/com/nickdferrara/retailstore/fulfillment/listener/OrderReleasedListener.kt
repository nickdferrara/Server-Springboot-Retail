package com.nickdferrara.retailstore.fulfillment.listener

import com.nickdferrara.retailstore.fulfillment.service.FulfillmentService
import com.nickdferrara.retailstore.orders.events.OrderReleasedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class OrderReleasedListener(
    private val fulfillmentService: FulfillmentService
) {

    @EventListener
    fun handleOrderReleasedEvent(event: OrderReleasedEvent) {
        fulfillmentService.createFulfillmentFromOrder(event.orderDetails)
    }
}
