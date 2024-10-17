package com.nickdferrara.retailstore.shipping.repository

import com.nickdferrara.retailstore.shipping.domain.ShippingRoute
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShippingRouteRepository : JpaRepository<ShippingRoute, Long>
