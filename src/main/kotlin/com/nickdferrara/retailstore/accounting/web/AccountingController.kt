package com.nickdferrara.retailstore.accounting.web

import com.nickdferrara.retailstore.accounting.domain.Accounting
import com.nickdferrara.retailstore.accounting.service.AccountingService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/accountings")
class AccountingController(private val accountingService: AccountingService) {

    @GetMapping
    fun getAllAccountings(): List<Accounting> = accountingService.findAllAccountings()

    @GetMapping("/{id}")
    fun getAccountingById(@PathVariable id: Long): ResponseEntity<Accounting> {
        val accounting = accountingService.findAccountingById(id)
        return if (accounting != null) {
            ResponseEntity.ok(accounting)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createAccounting(@RequestBody accounting: Accounting): Accounting = accountingService.createAccounting(accounting)

    @PutMapping("/{id}")
    fun updateAccounting(@PathVariable id: Long, @RequestBody accounting: Accounting): ResponseEntity<Accounting> {
        return try {
            ResponseEntity.ok(accountingService.updateAccounting(id, accounting))
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteAccounting(@PathVariable id: Long): ResponseEntity<Unit> {
        return try {
            accountingService.deleteAccounting(id)
            ResponseEntity.noContent().build()
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }
}
