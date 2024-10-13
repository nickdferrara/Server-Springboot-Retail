package com.nickdferrara.retailstore.store.repository

import com.nickdferrara.retailstore.store.domain.Store
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StoreRepository : JpaRepository<Store, Long> {
    fun findByStoreNumber(storeNumber: String): Store?
}
