package com.nickdferrara.retailstore.store.service

import com.nickdferrara.retailstore.store.domain.Store
import com.nickdferrara.retailstore.store.repository.StoreRepository
import org.springframework.stereotype.Service

@Service
class StoreService(private val storeRepository: StoreRepository) {
    fun getStoreByNumber(storeNumber: String): Store? {
        return storeRepository.findByStoreNumber(storeNumber)
    }
}
