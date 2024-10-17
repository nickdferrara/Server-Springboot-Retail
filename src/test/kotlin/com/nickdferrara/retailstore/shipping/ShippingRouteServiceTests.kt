package com.nickdferrara.retailstore.shipping

import com.nickdferrara.retailstore.shipping.domain.ShippingRoute
import com.nickdferrara.retailstore.shipping.repository.ShippingRouteRepository
import com.nickdferrara.retailstore.shipping.service.ShippingRouteService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.util.*

@SpringBootTest
class ShippingRouteServiceTests {

    private val shippingRouteRepository: ShippingRouteRepository = mock(ShippingRouteRepository::class.java)
    private val shippingRouteService: ShippingRouteService = ShippingRouteService(shippingRouteRepository)

    @Test
    fun `test findAllShippingRoutes`() {
        val shippingRoutes = listOf(
            ShippingRoute(1, 101, LocalDate.now(), "Carrier1", "Tracking1"),
            ShippingRoute(2, 102, LocalDate.now(), "Carrier2", "Tracking2")
        )
        `when`(shippingRouteRepository.findAll()).thenReturn(shippingRoutes)

        val result = shippingRouteService.findAllShippingRoutes()
        assertEquals(2, result.size)
        assertEquals(101, result[0].orderId)
        assertEquals(102, result[1].orderId)
    }

    @Test
    fun `test findShippingRouteById`() {
        val shippingRoute = ShippingRoute(1, 101, LocalDate.now(), "Carrier1", "Tracking1")
        `when`(shippingRouteRepository.findById(1)).thenReturn(Optional.of(shippingRoute))

        val result = shippingRouteService.findShippingRouteById(1)
        assertNotNull(result)
        assertEquals(101, result?.orderId)
    }

    @Test
    fun `test createShippingRoute`() {
        val shippingRoute = ShippingRoute(1, 101, LocalDate.now(), "Carrier1", "Tracking1")
        `when`(shippingRouteRepository.save(shippingRoute)).thenReturn(shippingRoute)

        val result = shippingRouteService.createShippingRoute(shippingRoute)
        assertNotNull(result)
        assertEquals(101, result.orderId)
    }

    @Test
    fun `test updateShippingRoute`() {
        val shippingRoute = ShippingRoute(1, 101, LocalDate.now(), "Carrier1", "Tracking1")
        `when`(shippingRouteRepository.existsById(1)).thenReturn(true)
        `when`(shippingRouteRepository.save(shippingRoute)).thenReturn(shippingRoute)

        val result = shippingRouteService.updateShippingRoute(1, shippingRoute)
        assertNotNull(result)
        assertEquals(101, result.orderId)
    }

    @Test
    fun `test deleteShippingRoute`() {
        `when`(shippingRouteRepository.existsById(1)).thenReturn(true)
        doNothing().`when`(shippingRouteRepository).deleteById(1)

        shippingRouteService.deleteShippingRoute(1)
        verify(shippingRouteRepository, times(1)).deleteById(1)
    }
}
