package com.nickdferrara.retailstore.shipping.repository

import com.nickdferrara.retailstore.shipping.domain.Shipping
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShippingRepository : JpaRepository<Shipping, Long>
