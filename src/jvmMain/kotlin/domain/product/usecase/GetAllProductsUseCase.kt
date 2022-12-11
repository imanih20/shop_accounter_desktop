package domain.product.usecase

import domain.product.model.Product
import domain.product.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetAllProductsUseCase(private val rep: ProductRepository) {
    suspend operator fun invoke() : Flow<List<Product>>{
        return rep.getALLProducts()
    }
}