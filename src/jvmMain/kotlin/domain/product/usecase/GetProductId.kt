package domain.product.usecase

import domain.product.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductId(private val rep : ProductRepository) {
    suspend operator fun invoke(title: String) : Flow<Int> {
        return rep.getProductId(title)
    }
}