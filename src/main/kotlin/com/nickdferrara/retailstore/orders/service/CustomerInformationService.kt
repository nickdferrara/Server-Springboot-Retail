package com.nickdferrara.retailstore.orders.service

import com.nickdferrara.retailstore.orders.domain.CustomerInformation
import com.nickdferrara.retailstore.orders.repository.CustomerInformationRepository
import org.springframework.stereotype.Service

@Service
class CustomerInformationService(
    private val customerInformationRepository: CustomerInformationRepository
) {
    fun createCustomerInformation(customerInformation: CustomerInformation) =
        customerInformationRepository.save(customerInformation)
}