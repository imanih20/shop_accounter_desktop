package domain.product.usecase

import domain.product.model.Product
import domain.product.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductUseCase(private val rep : ProductRepository) {
    suspend operator fun invoke(id: Int) : Flow<Product?> {
        return rep.getProduct(id)
    }
}