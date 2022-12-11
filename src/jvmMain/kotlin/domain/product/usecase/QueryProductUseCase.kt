package domain.product.usecase

import domain.product.model.Product
import domain.product.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class QueryProductUseCase(private val rep: ProductRepository) {
    suspend operator fun invoke(name: String,owner: String = "") : Flow<List<Product>> {
        return if (owner.isEmpty())
            rep.queryProductsByName(name)
        else
            rep.queryProductsByName(name,owner)
    }
}