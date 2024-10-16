package com.nickdferrara.retailstore

import org.junit.jupiter.api.Test
import org.springframework.modulith.core.ApplicationModules
import org.springframework.modulith.docs.Documenter

class ModularityTests {
    private val modules = ApplicationModules.of(Application::class.java)

    @Test
    fun `verify modular structure`() {
        modules.verify()
    }

    @Test
    fun `create documentation`() {
        Documenter(modules).writeModuleCanvases()
    }

    @Test
    fun `verify orders module`() {
        modules.getModuleByName("orders").ifPresent { it.verify() }
    }

    @Test
    fun `verify fulfillment module`() {
        modules.getModuleByName("fulfillment").ifPresent { it.verify() }
    }

    @Test
    fun `verify shipping module`() {
        modules.getModuleByName("shipping").ifPresent { it.verify() }
    }

    @Test
    fun `verify accounting module`() {
        modules.getModuleByName("accounting").ifPresent { it.verify() }
    }
}
