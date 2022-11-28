package domain.product.repository

import domain.product.model.Product

interface ProductRepository {
    suspend fun addProduct(product: Product)

    suspend fun getALLProducts() : List<Product>

    suspend fun queryProductsByName(name: String, owner: String) : List<Product>

    suspend fun queryProductsByDate(date: String) : List<Product>

    suspend fun editProduct(product: Product)

    suspend fun deleteProduct(id: String)
}