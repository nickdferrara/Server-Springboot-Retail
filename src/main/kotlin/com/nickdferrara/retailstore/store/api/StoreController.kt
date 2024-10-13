package com.nickdferrara.retailstore.store.api

import com.nickdferrara.retailstore.store.domain.Store
import com.nickdferrara.retailstore.store.service.StoreService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/stores")
class StoreController(private val storeService: StoreService) {
    
    @GetMapping("/{storeNumber}")
    fun getStoreByNumber(@PathVariable storeNumber: String): ResponseEntity<Store> {
        val store = storeService.getStoreByNumber(storeNumber)
        return if (store != null) {
            ResponseEntity.ok(store)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
