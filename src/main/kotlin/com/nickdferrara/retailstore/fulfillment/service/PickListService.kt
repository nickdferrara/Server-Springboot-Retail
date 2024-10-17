package com.nickdferrara.retailstore.fulfillment.service

import com.nickdferrara.retailstore.fulfillment.domain.PickList
import com.nickdferrara.retailstore.fulfillment.domain.PickListItem
import com.nickdferrara.retailstore.fulfillment.domain.PickListStatus
import com.nickdferrara.retailstore.fulfillment.repository.PickListRepository
import com.nickdferrara.retailstore.fulfillment.repository.PickListItemRepository
import com.nickdferrara.retailstore.fulfillment.mapper.PickListMapper
import com.nickdferrara.retailstore.fulfillment.mapper.PickListItemMapper
import com.nickdferrara.retailstore.orders.domain.Order
import org.springframework.stereotype.Service
import java.util.*

@Service
class PickListService(
    private val pickListRepository: PickListRepository,
    private val pickListItemRepository: PickListItemRepository,
    private val pickListMapper: PickListMapper,
    private val pickListItemMapper: PickListItemMapper
) {

    fun findAllPickLists(): List<PickList> = pickListRepository.findAll()

    fun findPickListById(id: Long): PickList? = pickListRepository.findById(id).orElse(null)

    fun createPickList(pickList: PickList): PickList = pickListRepository.save(pickList)

    fun updatePickList(id: Long, pickList: PickList): PickList {
        return if (pickListRepository.existsById(id)) {
            pickListRepository.save(pickList.copy(id = id))
        } else {
            throw NoSuchElementException("PickList with id $id not found")
        }
    }

    fun deletePickList(id: Long) {
        if (pickListRepository.existsById(id)) {
            pickListRepository.deleteById(id)
        } else {
            throw NoSuchElementException("PickList with id $id not found")
        }
    }

    fun createPickListFromOrder(order: Order): PickList {
        val pickListItems = order.orderItems.map { orderItem ->
            pickListItemMapper.toPickListItem(orderItem)
        }

        val pickList = pickListMapper.toPickList(order).copy(
            status = PickListStatus.PENDING,
            pickListItems = pickListItems
        )

        return pickListRepository.save(pickList)
    }
}
