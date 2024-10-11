package com.nickdferrara.retailstore.products.repository

import com.nickdferrara.retailstore.products.domain.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long>