package peresentation.base.components.main.components.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.financier.model.Financier
import domain.product.model.Product
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import peresentation.base.components.main.components.addProduct.reAssign
import peresentation.base.components.main.components.search.components.resultItemView
import peresentation.base.components.main.components.search.components.resultListHeader
import peresentation.base.components.main.components.search.components.resultShowView
import peresentation.base.components.main.components.search.components.searchInputView
import peresentation.common.components.adaptiveLayout
import peresentation.common.components.dropDownTextFiled
import peresentation.common.components.inputTextFiled

@Composable
fun searchView(){
    val controller : SearchViewController by inject(SearchViewController::class.java)
    val scope = rememberCoroutineScope()
    val financiers = remember { mutableStateListOf<Financier>() }
    val products = remember { mutableStateListOf<Product>() }
    val filteredProducts = remember { mutableStateListOf<Product>() }
    suspend fun refreshLists() {
        controller.getProducts().collect{
            products.reAssign(it)
            filteredProducts.reAssign(it)
        }
    }
    scope.launch {
        controller.getFinanciers().collect{
            financiers.reAssign(it)
        }
        refreshLists()
    }
    adaptiveLayout(
        { modifier ->
            searchInputView(modifier,financiers){query, owner ->
                filteredProducts.reAssign(products.filter { it.title.contains(query) && it.owner.contains(owner) })
            }
        },{modifier ->
            resultShowView(
                modifier,
                filteredProducts,
                financiers,
                {
                    scope.launch {
                        controller.deleteProduct(it)
                        refreshLists()
                    }
                },{
                    scope.launch {
                        controller.editProduct(it)
                        refreshLists()
                    }
                }
            )

        }
    )
}
