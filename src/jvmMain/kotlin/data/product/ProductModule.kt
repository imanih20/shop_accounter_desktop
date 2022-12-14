package data.product

import data.product.repository.ProductRepositoryImpl
import domain.product.repository.ProductRepository
import domain.product.usecase.*
import org.koin.dsl.module

val productModule = module {
    single<ProductRepository> { ProductRepositoryImpl() }

    single { AddProductUseCase(get()) }

    single { DeleteProductUseCase(get()) }

    single { EditProductUseCase(get()) }

    single { GetAllProductsUseCase(get()) }

    single { ReduceProductQuantityUseCase(get()) }

    single { IncreaseProductQuantityUseCase(get()) }
}