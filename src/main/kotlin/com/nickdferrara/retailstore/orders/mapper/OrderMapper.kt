package com.nickdferrara.retailstore.orders.mapper

import com.nickdferrara.retailstore.orders.domain.Order
import com.nickdferrara.retailstore.orders.domain.OrderItem
import com.nickdferrara.retailstore.orders.domain.CustomerInformation
import com.nickdferrara.retailstore.orders.dto.OrderRequest
import com.nickdferrara.retailstore.orders.dto.OrderItemRequest
import com.nickdferrara.retailstore.orders.dto.StoreRequest
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring", uses = [OrderItemMapper::class])
interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customerInformation", source = "customerInformation")
    fun toOrder(orderRequest: OrderRequest): Order

    fun toOrderRequest(order: Order): OrderRequest

    fun toCustomerInformation(storeRequest: StoreRequest): CustomerInformation
}
