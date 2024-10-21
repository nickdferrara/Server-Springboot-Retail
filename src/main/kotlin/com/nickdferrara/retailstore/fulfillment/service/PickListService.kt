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

    fun updatePickList(pickList: PickList): PickList {
        val existingPickList = pickListRepository.findByOrderNumber(pickList.orderNumber)
            ?: throw NoSuchElementException("Order number ${pickList.orderNumber} not found");

        val updatedPickList = pickList.copy(
            id = existingPickList.id,
            status = pickList.status,
            pickListItems = pickList.pickListItems,
            pickListCustomer = pickList.pickListCustomer
        )

        val savedPickList = pickListRepository.save(updatedPickList)

        if (savedPickList.status == PickListStatus.COMPLETED) {
            eventPublisher.publishEvent(PickListCompleteEvent(updatedPickList.id, updatedPickList))
        }

        return savedPickList
    }

    fun deletePickList(id: Long) {
        if (pickListRepository.existsById(id)) {
            pickListRepository.deleteById(id)
        } else {
            throw NoSuchElementException("PickList with id $id not found")
        }
    }
}
