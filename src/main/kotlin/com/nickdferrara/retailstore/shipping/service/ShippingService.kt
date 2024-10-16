package com.nickdferrara.retailstore.shipping.service

import com.nickdferrara.retailstore.shipping.domain.Shipping
import com.nickdferrara.retailstore.shipping.repository.ShippingRepository
import org.springframework.stereotype.Service

@Service
class ShippingService(private val shippingRepository: ShippingRepository) {

    fun findAllShippings(): List<Shipping> = shippingRepository.findAll()

    fun findShippingById(id: Long): Shipping? = shippingRepository.findById(id).orElse(null)

    fun createShipping(shipping: Shipping): Shipping = shippingRepository.save(shipping)

    fun updateShipping(id: Long, shipping: Shipping): Shipping {
        return if (shippingRepository.existsById(id)) {
            shippingRepository.save(shipping.copy(id = id))
        } else {
            throw NoSuchElementException("Shipping with id $id not found")
        }
    }

    fun deleteShipping(id: Long) {
        if (shippingRepository.existsById(id)) {
            shippingRepository.deleteById(id)
        } else {
            throw NoSuchElementException("Shipping with id $id not found")
        }
    }
}
