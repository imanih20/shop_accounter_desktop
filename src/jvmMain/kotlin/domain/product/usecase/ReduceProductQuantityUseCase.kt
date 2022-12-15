package domain.product.usecase

import domain.product.repository.ProductRepository

class ReduceProductQuantityUseCase(private val rep: ProductRepository) {
    suspend operator fun invoke(name: String, quantity: Double) {
        rep.reduceProductQuantity(name, quantity)
    }
}