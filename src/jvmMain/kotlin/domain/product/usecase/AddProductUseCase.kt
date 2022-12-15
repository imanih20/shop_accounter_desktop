package domain.product.usecase

import domain.product.model.Product
import domain.product.repository.ProductRepository

class AddProductUseCase(private val rep: ProductRepository) {
    suspend operator fun invoke(product: Product) {
        rep.saveProduct(product)
    }
}