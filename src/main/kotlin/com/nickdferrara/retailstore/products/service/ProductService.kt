package com.nickdferrara.retailstore.products.service

import com.nickdferrara.retailstore.products.domain.Product
import com.nickdferrara.retailstore.products.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun findAllProducts(): List<Product> = productRepository.findAll()

    fun findProductById(id: Long): Product? = productRepository.findById(id).orElse(null)

    fun createProduct(product: Product): Product = productRepository.save(product)

    fun updateProduct(id: Long, product: Product): Product {
        return if (productRepository.existsById(id)) {
            productRepository.save(product.copy(id = id))
        } else {
            throw NoSuchElementException("Product with id $id not found")
        }
    }

    fun deleteProduct(id: Long) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id)
        } else {
            throw NoSuchElementException("Product with id $id not found")
        }
    }
}