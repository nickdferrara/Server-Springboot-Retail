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
}