package peresentation.base.components.main.components.search

import common.utils.Controller
import domain.financier.model.Financier
import domain.financier.usecases.GetAllFinanciersUseCase
import domain.product.model.Product
import domain.product.usecase.DeleteProductUseCase
import domain.product.usecase.EditProductUseCase
import domain.product.usecase.GetAllProductsUseCase
import kotlinx.coroutines.flow.Flow
import peresentation.common.components.deleteDialog

class SearchViewController(
    private val getAllProducts: GetAllProductsUseCase,
    private val getAllFinanciers: GetAllFinanciersUseCase,
    private val editPr: EditProductUseCase,
    private val delProduct: DeleteProductUseCase
) : Controller {
    suspend fun getProducts () : Flow<List<Product>>{
        return getAllProducts()
    }

    suspend fun getFinanciers() : Flow<List<Financier>>{
        return getAllFinanciers()
    }

    suspend fun editProduct(product: Product){
        editPr(product)
    }

    suspend fun deleteProduct(id: Int) {
        delProduct(id)
    }
}