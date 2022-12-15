package peresentation.base.components.main.components.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import domain.financier.model.Financier
import domain.product.model.Product
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import peresentation.base.components.main.components.addProduct.reAssign
import peresentation.base.components.main.components.search.components.resultShowView
import peresentation.base.components.main.components.search.components.searchInputView
import peresentation.common.components.adaptiveLayout

@Composable
fun searchView() {
    val controller: SearchViewController by inject(SearchViewController::class.java)
    val scope = rememberCoroutineScope()
    val financiers = remember { mutableStateListOf<Financier>() }
    val products = remember { mutableStateListOf<Product>() }
    val filteredProducts = remember { mutableStateListOf<Product>() }
    suspend fun refreshLists() {
        controller.getProducts().collect {
            products.reAssign(it)
            filteredProducts.reAssign(it)
        }
    }
    scope.launch {
        controller.getFinanciers().collect {
            financiers.reAssign(it)
        }
        refreshLists()
    }
    adaptiveLayout(
        { modifier ->
            searchInputView(modifier, financiers) { query, owner ->
                filteredProducts.reAssign(products.filter { it.title.contains(query) && it.owner.contains(owner) })
            }
        }, { modifier ->
            resultShowView(
                modifier,
                filteredProducts,
                financiers,
                {
                    scope.launch {
                        controller.deleteProduct(it)
                        refreshLists()
                    }
                }, {
                    scope.launch {
                        controller.editProduct(it)
                        refreshLists()
                    }
                }
            )

        }
    )
}
