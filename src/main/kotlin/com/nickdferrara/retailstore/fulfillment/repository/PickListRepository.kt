package com.nickdferrara.retailstore.fulfillment.repository

import com.nickdferrara.retailstore.fulfillment.domain.PickList
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PickListRepository : JpaRepository<PickList, Long>
