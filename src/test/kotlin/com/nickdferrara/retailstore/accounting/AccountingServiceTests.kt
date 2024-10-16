package com.nickdferrara.retailstore.accounting

import com.nickdferrara.retailstore.accounting.domain.Accounting
import com.nickdferrara.retailstore.accounting.repository.AccountingRepository
import com.nickdferrara.retailstore.accounting.service.AccountingService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@SpringBootTest
class AccountingServiceTests {

    private val accountingRepository: AccountingRepository = mock(AccountingRepository::class.java)
    private val accountingService: AccountingService = AccountingService(accountingRepository)

    @Test
    fun `test findAllAccountings`() {
        val accountings = listOf(
            Accounting(1, 101, LocalDate.now(), BigDecimal(100)),
            Accounting(2, 102, LocalDate.now(), BigDecimal(200))
        )
        `when`(accountingRepository.findAll()).thenReturn(accountings)

        val result = accountingService.findAllAccountings()
        assertEquals(2, result.size)
        assertEquals(101, result[0].orderId)
        assertEquals(102, result[1].orderId)
    }

    @Test
    fun `test findAccountingById`() {
        val accounting = Accounting(1, 101, LocalDate.now(), BigDecimal(100))
        `when`(accountingRepository.findById(1)).thenReturn(Optional.of(accounting))

        val result = accountingService.findAccountingById(1)
        assertNotNull(result)
        assertEquals(101, result?.orderId)
    }

    @Test
    fun `test createAccounting`() {
        val accounting = Accounting(1, 101, LocalDate.now(), BigDecimal(100))
        `when`(accountingRepository.save(accounting)).thenReturn(accounting)

        val result = accountingService.createAccounting(accounting)
        assertNotNull(result)
        assertEquals(101, result.orderId)
    }

    @Test
    fun `test updateAccounting`() {
        val accounting = Accounting(1, 101, LocalDate.now(), BigDecimal(100))
        `when`(accountingRepository.existsById(1)).thenReturn(true)
        `when`(accountingRepository.save(accounting)).thenReturn(accounting)

        val result = accountingService.updateAccounting(1, accounting)
        assertNotNull(result)
        assertEquals(101, result.orderId)
    }

    @Test
    fun `test deleteAccounting`() {
        `when`(accountingRepository.existsById(1)).thenReturn(true)
        doNothing().`when`(accountingRepository).deleteById(1)

        accountingService.deleteAccounting(1)
        verify(accountingRepository, times(1)).deleteById(1)
    }
}
