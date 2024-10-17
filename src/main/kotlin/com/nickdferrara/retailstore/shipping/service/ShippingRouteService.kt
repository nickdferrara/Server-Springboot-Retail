package com.nickdferrara.retailstore.shipping.service

import com.nickdferrara.retailstore.shipping.domain.ShippingRoute
import com.nickdferrara.retailstore.shipping.repository.ShippingRouteRepository
import org.springframework.stereotype.Service

@Service
class ShippingRouteService(private val shippingRouteRepository: ShippingRouteRepository) {

    fun findAllShippingRoutes(): List<ShippingRoute> = shippingRouteRepository.findAll()

    fun findShippingRouteById(id: Long): ShippingRoute? = shippingRouteRepository.findById(id).orElse(null)

    fun createShippingRoute(shippingRoute: ShippingRoute): ShippingRoute = shippingRouteRepository.save(shippingRoute)

    fun updateShippingRoute(id: Long, shippingRoute: ShippingRoute): ShippingRoute {
        return if (shippingRouteRepository.existsById(id)) {
            shippingRouteRepository.save(shippingRoute.copy(id = id))
        } else {
            throw NoSuchElementException("ShippingRoute with id $id not found")
        }
    }

    fun deleteShippingRoute(id: Long) {
        if (shippingRouteRepository.existsById(id)) {
            shippingRouteRepository.deleteById(id)
        } else {
            throw NoSuchElementException("ShippingRoute with id $id not found")
        }
    }
}
