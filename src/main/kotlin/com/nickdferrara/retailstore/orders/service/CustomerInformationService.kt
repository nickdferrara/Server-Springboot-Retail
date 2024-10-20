package com.nickdferrara.retailstore.orders.service

import com.nickdferrara.retailstore.orders.domain.CustomerInformation
import com.nickdferrara.retailstore.orders.repository.CustomerInformationRepository
import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class CustomerInformationService(
    private val customerInformationRepository: CustomerInformationRepository
) {
    fun createCustomerInformation(customerInformation: CustomerInformation) =
        customerInformationRepository.save(customerInformation)

    fun deleteCustomerInformation(id: Long) {
        if (customerInformationRepository.existsById(id)) {
            customerInformationRepository.deleteById(id)
        } else {
            throw NoSuchElementException("OrderItem with id $id not found")
        }
    }
}