package com.nickdferrara.retailstore.fulfillment.repository

import com.nickdferrara.retailstore.fulfillment.domain.PickListItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PickListItemRepository : JpaRepository<PickListItem, Long>
