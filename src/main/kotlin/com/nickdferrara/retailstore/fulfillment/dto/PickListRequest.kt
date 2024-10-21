package com.nickdferrara.retailstore.fulfillment.dto

data class PickListRequest(
    val orderNumber: String,
    val status: String,
    val pickListItems: List<PickListItemRequest>,
    val pickListCustomer: PickListCustomerRequest
)