package domain.product.repository

import domain.product.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun saveProduct(product: Product)

    suspend fun addProduct(product: Product)

    suspend fun getALLProducts(): Flow<List<Product>>

    suspend fun editProduct(product: Product)

    suspend fun deleteProduct(productId: Int)

    suspend fun reduceProductQuantity(name: String, quantity: Double)

}