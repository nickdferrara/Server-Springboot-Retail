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

import com.nickdferrara.retailstore.fulfillment.events.PickListCompleteEvent
import org.springframework.context.ApplicationEventPublisher

@Service
class PickListService(
    private val pickListRepository: PickListRepository,
    private val pickListItemService: PickListItemService,
    private val pickListCustomerService: PickListCustomerService,
    private val eventPublisher: ApplicationEventPublisher
) {

    fun findAllPickLists(): List<PickList> = pickListRepository.findAll()

    fun findPickListById(id: Long): PickList? = pickListRepository.findById(id).orElse(null)

    fun createPickList(pickList: PickList): PickList {
        pickList.pickListItems.forEach { pickListItemService.createPickListItem(it) }
            .also { pickListCustomerService.createPickListCustomer(pickList.pickListCustomer) }
        val updatedPickList = pickList.copy(status = PickListStatus.PENDING)
        return pickListRepository.save(updatedPickList)
    }

    fun updatePickList(id: Long, pickList: PickList): PickList {
        if (pickListRepository.existsById(id)) {
            val updatedPickList = pickListRepository.save(pickList.copy(id = id))
            if (updatedPickList.status == PickListStatus.COMPLETED) {
                eventPublisher.publishEvent(PickListCompleteEvent(updatedPickList.id, updatedPickList))
            }
            return updatedPickList
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
}
