package com.nickdferrara.retailstore.fulfillment.web

import com.nickdferrara.retailstore.fulfillment.domain.PickList
import com.nickdferrara.retailstore.fulfillment.service.PickListService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/picklists")
class PickListController(private val pickListService: PickListService) {

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

    @PutMapping("/{id}")
    fun updatePickList(@PathVariable id: Long, @RequestBody pickList: PickList): ResponseEntity<PickList> {
        return try {
            val updatedPickList = pickListService.updatePickList(id, pickList)
            if (updatedPickList.status == PickListStatus.COMPLETED) {
                eventPublisher.publishEvent(PickListCompleteEvent(updatedPickList.id, updatedPickList))
            }
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
