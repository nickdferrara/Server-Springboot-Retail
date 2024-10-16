package com.nickdferrara.retailstore.store.service

import com.nickdferrara.retailstore.store.dto.StoreResponse
import com.nickdferrara.retailstore.store.extension.store.ToStoreResponse
import com.nickdferrara.retailstore.store.StoreApi
import com.nickdferrara.retailstore.store.repository.StoreRepository
import org.springframework.stereotype.Service

@Service
class StoreService(private val storeRepository: StoreRepository) : StoreApi {
    override fun findStoreByNumber(storeNumber: String): StoreResponse? {
        val store = storeRepository.findByStoreNumber(storeNumber)
        return store?.ToStoreResponse()
    }
}
