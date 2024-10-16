package com.nickdferrara.retailstore.shipping

import com.nickdferrara.retailstore.shipping.domain.Shipping
import com.nickdferrara.retailstore.shipping.repository.ShippingRepository
import com.nickdferrara.retailstore.shipping.service.ShippingService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.util.*

@SpringBootTest
class ShippingServiceTests {

    private val shippingRepository: ShippingRepository = mock(ShippingRepository::class.java)
    private val shippingService: ShippingService = ShippingService(shippingRepository)

    @Test
    fun `test findAllShippings`() {
        val shippings = listOf(
            Shipping(1, 101, LocalDate.now(), "Carrier1", "Tracking1"),
            Shipping(2, 102, LocalDate.now(), "Carrier2", "Tracking2")
        )
        `when`(shippingRepository.findAll()).thenReturn(shippings)

        val result = shippingService.findAllShippings()
        assertEquals(2, result.size)
        assertEquals(101, result[0].orderId)
        assertEquals(102, result[1].orderId)
    }

    @Test
    fun `test findShippingById`() {
        val shipping = Shipping(1, 101, LocalDate.now(), "Carrier1", "Tracking1")
        `when`(shippingRepository.findById(1)).thenReturn(Optional.of(shipping))

        val result = shippingService.findShippingById(1)
        assertNotNull(result)
        assertEquals(101, result?.orderId)
    }

    @Test
    fun `test createShipping`() {
        val shipping = Shipping(1, 101, LocalDate.now(), "Carrier1", "Tracking1")
        `when`(shippingRepository.save(shipping)).thenReturn(shipping)

        val result = shippingService.createShipping(shipping)
        assertNotNull(result)
        assertEquals(101, result.orderId)
    }

    @Test
    fun `test updateShipping`() {
        val shipping = Shipping(1, 101, LocalDate.now(), "Carrier1", "Tracking1")
        `when`(shippingRepository.existsById(1)).thenReturn(true)
        `when`(shippingRepository.save(shipping)).thenReturn(shipping)

        val result = shippingService.updateShipping(1, shipping)
        assertNotNull(result)
        assertEquals(101, result.orderId)
    }

    @Test
    fun `test deleteShipping`() {
        `when`(shippingRepository.existsById(1)).thenReturn(true)
        doNothing().`when`(shippingRepository).deleteById(1)

        shippingService.deleteShipping(1)
        verify(shippingRepository, times(1)).deleteById(1)
    }
}
