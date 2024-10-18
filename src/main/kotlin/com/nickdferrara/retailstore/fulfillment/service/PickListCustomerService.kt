package com.nickdferrara.retailstore.fulfillment.service

import com.nickdferrara.retailstore.fulfillment.domain.PickListCustomer
import com.nickdferrara.retailstore.fulfillment.repository.PickListCustomerRepository
import org.springframework.stereotype.Service

@Service
class PickListCustomerService(
    private val pickListCustomerRepository: PickListCustomerRepository
) {
    fun findAllPickListCustomers() = pickListCustomerRepository.findAll()

    fun findPickListCustomerById(id: Long) = pickListCustomerRepository.findById(id).orElse(null)

    fun createPickListCustomer(pickListCustomer: PickListCustomer) = pickListCustomerRepository.save(pickListCustomer)
}