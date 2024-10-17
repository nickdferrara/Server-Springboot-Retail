package com.nickdferrara.retailstore.orders.mapper

import com.nickdferrara.retailstore.orders.domain.OrderItem
import com.nickdferrara.retailstore.orders.dto.OrderItemRequest
import org.mapstruct.*

@Mapper(componentModel = "spring")
interface OrderItemMapper {
    @Mapping(target = "id", ignore = true)
    fun toOrderItem(orderItemRequest: OrderItemRequest): OrderItem
}