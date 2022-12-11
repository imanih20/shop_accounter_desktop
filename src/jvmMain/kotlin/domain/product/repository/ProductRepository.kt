package domain.product.repository

import domain.product.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun saveProduct(product: Product)

    suspend fun addProduct(product: Product)

    suspend fun getProduct(id: Int) : Flow<Product?>

    suspend fun getProductId(title: String) : Flow<Int>

    suspend fun getALLProducts() : Flow<List<Product>>

    suspend fun queryProductsByName(title: String, owner: String) : Flow<List<Product>>

    suspend fun queryProductsByName(title: String) : Flow<List<Product>>

    suspend fun editProduct(product: Product)

    suspend fun deleteProduct(productId: Int)

    suspend fun reduceProductQuantity(name: String,quantity: Double)

}