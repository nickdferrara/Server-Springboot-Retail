package com.nickdferrara.retailstore.store.web

import com.nickdferrara.retailstore.store.domain.Store
import com.nickdferrara.retailstore.store.dto.StoreRequest
import com.nickdferrara.retailstore.store.dto.StoreResponse
import com.nickdferrara.retailstore.store.service.StoreService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/stores")
class StoreController(private val storeService: StoreService) {

    @GetMapping
    fun getAllStores(): List<StoreResponse> = storeService.findAllStores()

    @GetMapping("/{id}")
    fun getStoreById(@PathVariable id: Long): ResponseEntity<StoreResponse> {
        val store = storeService.findStoreById(id)
        return if (store != null) {
            ResponseEntity.ok(store)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createStore(@RequestBody storeRequest: StoreRequest): StoreResponse {
        return storeService.createStore(storeRequest)
    }

    @PutMapping("/{id}")
    fun updateStore(@PathVariable id: Long, @RequestBody storeRequest: StoreRequest): ResponseEntity<StoreResponse> {
        return try {
            ResponseEntity.ok(storeService.updateStore(id, storeRequest))
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteStore(@PathVariable id: Long): ResponseEntity<Unit> {
        return try {
            storeService.deleteStore(id)
            ResponseEntity.noContent().build()
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }
}
