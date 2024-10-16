package com.nickdferrara.retailstore.accounting.service

import com.nickdferrara.retailstore.accounting.domain.Accounting
import com.nickdferrara.retailstore.accounting.repository.AccountingRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountingService(private val accountingRepository: AccountingRepository) {

    fun findAllAccountings(): List<Accounting> = accountingRepository.findAll()

    fun findAccountingById(id: Long): Accounting? = accountingRepository.findById(id).orElse(null)

    fun createAccounting(accounting: Accounting): Accounting = accountingRepository.save(accounting)

    fun updateAccounting(id: Long, accounting: Accounting): Accounting {
        return if (accountingRepository.existsById(id)) {
            accountingRepository.save(accounting.copy(id = id))
        } else {
            throw NoSuchElementException("Accounting with id $id not found")
        }
    }

    fun deleteAccounting(id: Long) {
        if (accountingRepository.existsById(id)) {
            accountingRepository.deleteById(id)
        } else {
            throw NoSuchElementException("Accounting with id $id not found")
        }
    }
}
