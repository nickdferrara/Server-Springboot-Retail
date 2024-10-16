package com.nickdferrara.retailstore.fulfillment.repository

import com.nickdferrara.retailstore.fulfillment.domain.Fulfillment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FulfillmentRepository : JpaRepository<Fulfillment, Long>
