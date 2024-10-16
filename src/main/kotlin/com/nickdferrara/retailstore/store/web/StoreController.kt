package com.nickdferrara.retailstore.store.web

import com.nickdferrara.retailstore.store.service.StoreService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/stores")
class StoreController(private val storeService: StoreService) {

}
