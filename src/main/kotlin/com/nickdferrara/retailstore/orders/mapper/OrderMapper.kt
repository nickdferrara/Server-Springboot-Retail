package com.nickdferrara.retailstore.orders.mapper

import com.nickdferrara.retailstore.orders.domain.Order
import com.nickdferrara.retailstore.orders.domain.OrderItem
import com.nickdferrara.retailstore.orders.dto.OrderRequest
import com.nickdferrara.retailstore.orders.dto.OrderItemRequest
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    fun toOrder(orderRequest: OrderRequest): Order

    fun toOrderRequest(order: Order): OrderRequest

    @Mapping(target = "id", ignore = true)
    fun toOrderItem(orderItemRequest: OrderItemRequest): OrderItem
}
