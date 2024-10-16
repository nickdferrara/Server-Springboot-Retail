package com.nickdferrara.retailstore.store

import com.nickdferrara.retailstore.store.dto.StoreResponse
import org.springframework.stereotype.Service

@Service
interface StoreApi {
    fun findStoreByNumber(storeNumber: String): StoreResponse?
}