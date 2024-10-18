package com.nickdferrara.retailstore.fulfillment.service

import com.nickdferrara.retailstore.fulfillment.domain.PickListItem
import com.nickdferrara.retailstore.fulfillment.repository.PickListItemRepository
import org.springframework.stereotype.Service

@Service
class PickListItemService (
    private val pickListItemRepository: PickListItemRepository
) {
    fun findAllPickListItems() = pickListItemRepository.findAll()

    fun findPickListItemById(id: Long) = pickListItemRepository.findById(id).orElse(null)

    fun createPickListItem(pickListItem: PickListItem) = pickListItemRepository.save(pickListItem)
}
