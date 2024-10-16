package com.nickdferrara.retailstore

import org.junit.jupiter.api.Test
import org.springframework.modulith.core.ApplicationModules
import org.springframework.modulith.docs.Documenter

class ModularityTests {
    private val modules = ApplicationModules.of(Application::class.java)

    @Test
    fun `verify modular structure`() {
        ApplicationModules.of(Application::class.java).verify()
    }

    @Test
    fun `create documentation`() {
        Documenter(modules).writeModuleCanvases()
    }
}
