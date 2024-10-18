package com.nickdferrara.retailstore.orders.repository

import com.nickdferrara.retailstore.orders.domain.CustomerInformation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerInformationRepository : JpaRepository<CustomerInformation, Long>