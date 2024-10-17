package com.nickdferrara.retailstore.fulfillment.events

import com.nickdferrara.retailstore.fulfillment.domain.PickList

data class PickListCompleteEvent(
    val pickListId: Long,
    val pickList: PickList
)
