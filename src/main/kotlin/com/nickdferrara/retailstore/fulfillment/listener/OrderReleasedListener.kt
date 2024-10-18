package com.nickdferrara.retailstore.fulfillment.listener

import com.nickdferrara.retailstore.fulfillment.domain.PickListStatus
import com.nickdferrara.retailstore.fulfillment.mapper.*
import com.nickdferrara.retailstore.fulfillment.service.PickListService
import com.nickdferrara.retailstore.orders.events.OrderReleasedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class OrderReleasedListener(
    private val pickListService: PickListService,
    private val pickListMapper: PickListMapper,
) {

    @EventListener
    fun handleOrderReleasedEvent(event: OrderReleasedEvent) {
        val pickList = pickListMapper.toPickList(event.orderDetails)
        pickListService.createPickList(pickList)
    }
}
