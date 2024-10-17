package com.nickdferrara.retailstore.fulfillment

import com.nickdferrara.retailstore.fulfillment.domain.Fulfillment
import com.nickdferrara.retailstore.fulfillment.domain.FulfillmentStatus
import com.nickdferrara.retailstore.fulfillment.repository.FulfillmentRepository
import com.nickdferrara.retailstore.fulfillment.service.FulfillmentService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.util.*

@SpringBootTest
class FulfillmentServiceTests {

    private val fulfillmentRepository: FulfillmentRepository = mock(FulfillmentRepository::class.java)
    private val fulfillmentService: FulfillmentService = FulfillmentService(fulfillmentRepository)

    @Test
    fun `test findAllFulfillments`() {
        val fulfillments = listOf(
            Fulfillment(1, 101, LocalDate.now(), FulfillmentStatus.PENDING),
            Fulfillment(2, 102, LocalDate.now(), FulfillmentStatus.COMPLETED)
        )
        `when`(fulfillmentRepository.findAll()).thenReturn(fulfillments)

        val result = fulfillmentService.findAllFulfillments()
        assertEquals(2, result.size)
        assertEquals(101, result[0].orderId)
        assertEquals(102, result[1].orderId)
    }

    @Test
    fun `test findFulfillmentById`() {
        val fulfillment = Fulfillment(1, 101, LocalDate.now(), FulfillmentStatus.PENDING)
        `when`(fulfillmentRepository.findById(1)).thenReturn(Optional.of(fulfillment))

        val result = fulfillmentService.findFulfillmentById(1)
        assertNotNull(result)
        assertEquals(101, result?.orderId)
    }

    @Test
    fun `test createFulfillment`() {
        val fulfillment = Fulfillment(1, 101, LocalDate.now(), FulfillmentStatus.PENDING)
        `when`(fulfillmentRepository.save(fulfillment)).thenReturn(fulfillment)

        val result = fulfillmentService.createFulfillment(fulfillment)
        assertNotNull(result)
        assertEquals(101, result.orderId)
    }

    @Test
    fun `test updateFulfillment`() {
        val fulfillment = Fulfillment(1, 101, LocalDate.now(), FulfillmentStatus.PENDING)
        `when`(fulfillmentRepository.existsById(1)).thenReturn(true)
        `when`(fulfillmentRepository.save(fulfillment)).thenReturn(fulfillment)

        val result = fulfillmentService.updateFulfillment(1, fulfillment)
        assertNotNull(result)
        assertEquals(101, result.orderId)
    }

    @Test
    fun `test deleteFulfillment`() {
        `when`(fulfillmentRepository.existsById(1)).thenReturn(true)
        doNothing().`when`(fulfillmentRepository).deleteById(1)

        fulfillmentService.deleteFulfillment(1)
        verify(fulfillmentRepository, times(1)).deleteById(1)
    }
}
