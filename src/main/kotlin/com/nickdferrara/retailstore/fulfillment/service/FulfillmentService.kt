package com.nickdferrara.retailstore.fulfillment.service

import com.nickdferrara.retailstore.fulfillment.domain.Fulfillment
import com.nickdferrara.retailstore.fulfillment.repository.FulfillmentRepository
import com.nickdferrara.retailstore.orders.domain.Order
import org.springframework.stereotype.Service
import java.util.*

@Service
class FulfillmentService(private val fulfillmentRepository: FulfillmentRepository) {

    fun findAllFulfillments(): List<Fulfillment> = fulfillmentRepository.findAll()

    fun findFulfillmentById(id: Long): Fulfillment? = fulfillmentRepository.findById(id).orElse(null)

    fun createFulfillment(fulfillment: Fulfillment): Fulfillment = fulfillmentRepository.save(fulfillment)

    fun updateFulfillment(id: Long, fulfillment: Fulfillment): Fulfillment {
        return if (fulfillmentRepository.existsById(id)) {
            fulfillmentRepository.save(fulfillment.copy(id = id))
        } else {
            throw NoSuchElementException("Fulfillment with id $id not found")
        }
    }

    fun deleteFulfillment(id: Long) {
        if (fulfillmentRepository.existsById(id)) {
            fulfillmentRepository.deleteById(id)
        } else {
            throw NoSuchElementException("Fulfillment with id $id not found")
        }
    }

    fun createFulfillmentFromOrder(order: Order): Fulfillment {
        val fulfillment = Fulfillment(
            orderId = order.id,
            fulfillmentDate = LocalDate.now(),
            status = "CREATED"
        )
        return fulfillmentRepository.save(fulfillment)
    }
}
