package com.nickdferrara.retailstore.orders.mapper

import com.nickdferrara.retailstore.orders.domain.Order
import com.nickdferrara.retailstore.orders.dto.OrderRequest
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring", uses = [OrderItemMapper::class, CustomerInformationMapper::class])
interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    fun toOrder(orderRequest: OrderRequest): Order

    fun toOrderRequest(order: Order): OrderRequest

}
