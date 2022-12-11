package domain.product.usecase

import domain.product.repository.ProductRepository

class DeleteProductUseCase(private val rep : ProductRepository) {
    suspend operator fun invoke(id: Int) {
        rep.deleteProduct(id)
    }
}