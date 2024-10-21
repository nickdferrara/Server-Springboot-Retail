package com.nickdferrara.retailstore.fulfillment.web

import com.nickdferrara.retailstore.fulfillment.domain.PickList
import com.nickdferrara.retailstore.fulfillment.domain.PickListStatus
import com.nickdferrara.retailstore.fulfillment.dto.PickListRequest
import com.nickdferrara.retailstore.fulfillment.events.PickListCompleteEvent
import com.nickdferrara.retailstore.fulfillment.mapper.PickListMapper
import com.nickdferrara.retailstore.fulfillment.service.PickListService
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/picklists")
class PickListController(
    private val pickListService: PickListService,
    private val pickListMapper: PickListMapper
) {

    @GetMapping
    fun getAllPickLists(): List<PickList> = pickListService.findAllPickLists()

    @GetMapping("/{id}")
    fun getPickListById(@PathVariable id: Long): ResponseEntity<PickList> {
        val pickList = pickListService.findPickListById(id)
        return if (pickList != null) {
            ResponseEntity.ok(pickList)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPickList(@RequestBody pickList: PickList): PickList = pickListService.createPickList(pickList)

    @PutMapping
    fun updatePickList(
        @RequestBody request: PickListRequest
    ): ResponseEntity<PickList> {
        return try {
            val pickList = pickListMapper.fromPickListRequestToPicklist(request)
            val updatedPickList = pickListService.updatePickList(pickList)
            ResponseEntity.ok(updatedPickList)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deletePickList(@PathVariable id: Long): ResponseEntity<Unit> {
        return try {
            pickListService.deletePickList(id)
            ResponseEntity.noContent().build()
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }
}
