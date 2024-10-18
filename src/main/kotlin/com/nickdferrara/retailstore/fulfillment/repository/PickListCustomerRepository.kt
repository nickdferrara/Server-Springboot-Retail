package com.nickdferrara.retailstore.fulfillment.repository

import com.nickdferrara.retailstore.fulfillment.domain.PickListCustomer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PickListCustomerRepository : JpaRepository<PickListCustomer, Long>