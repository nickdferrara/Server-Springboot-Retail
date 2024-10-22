package com.nickdferrara.retailstore.store.service

import com.nickdferrara.retailstore.store.domain.Store
import com.nickdferrara.retailstore.store.dto.StoreRequest
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

    fun findAllStores(): List<StoreResponse> {
        return storeRepository.findAll().map { it.ToStoreResponse() }
    }

    fun findStoreById(id: Long): StoreResponse? {
        val store = storeRepository.findById(id).orElse(null)
        return store?.ToStoreResponse()
    }

    fun createStore(storeRequest: StoreRequest): StoreResponse {
        val store = Store(
            storeNumber = storeRequest.storeNumber,
            address = storeRequest.address,
            hoursOfOperation = storeRequest.hoursOfOperation
        )
        val savedStore = storeRepository.save(store)
        return savedStore.ToStoreResponse()
    }

    fun updateStore(id: Long, storeRequest: StoreRequest): StoreResponse {
        val existingStore = storeRepository.findById(id).orElseThrow { NoSuchElementException("Store with id $id not found") }
        val updatedStore = existingStore.copy(
            storeNumber = storeRequest.storeNumber,
            address = storeRequest.address,
            hoursOfOperation = storeRequest.hoursOfOperation
        )
        val savedStore = storeRepository.save(updatedStore)
        return savedStore.ToStoreResponse()
    }

    fun deleteStore(id: Long) {
        if (storeRepository.existsById(id)) {
            storeRepository.deleteById(id)
        } else {
            throw NoSuchElementException("Store with id $id not found")
        }
    }
}
