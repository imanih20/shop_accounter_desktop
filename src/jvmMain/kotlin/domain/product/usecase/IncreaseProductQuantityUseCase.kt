package domain.product.usecase

import domain.product.repository.ProductRepository

class IncreaseProductQuantityUseCase(private val rep: ProductRepository) {
    suspend operator fun invoke(name: String,quantity: Double){
        rep.increaseProductQuantity(name,quantity)
    }
}