package com.nickdferrara.retailstore.products

import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun getAllProducts(): List<Product> = productRepository.findAll()

    fun getProductById(id: Long): Product? = productRepository.findById(id).orElse(null)

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