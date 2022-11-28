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
import peresentation.base.components.main.components.search.components.resultItemView
import peresentation.base.components.main.components.search.components.resultListHeader
import peresentation.common.components.adaptiveLayout
import peresentation.common.components.dropDownTextFiled
import peresentation.common.components.inputTextFiled

@Composable
fun searchView(){
    val controller = SearchViewController()
    var query by remember { mutableStateOf("") }
    var owner by remember { mutableStateOf("") }
    adaptiveLayout(
        { modifier ->
            searchInputView(modifier,query,{q,o->
                query = q
                owner = o
            }, emptyList())
        },{modifier ->
            resultShowView(modifier,controller.searchProducts(query,owner))
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun resultShowView(modifier: Modifier = Modifier, list: List<Product>,) {
    LazyColumn(modifier.padding(10.dp)) {
        stickyHeader {
            resultListHeader()
        }
        items(list){
            resultItemView(product = it)
            Spacer(Modifier.size(5.dp))
        }
    }
}

@Composable
fun searchInputView(modifier: Modifier = Modifier,query: String,onQueryChange: (query:String,owner:String)->Unit,financiers: List<Financier>) {
    var owner by remember { mutableStateOf("") }
    Column (modifier){
        Row {
            inputTextFiled(
                query,
                {
                    onQueryChange(it,owner)
                },
                label = "اسم کالای مورد نظر  را وارد کنید"
            )
            Spacer(Modifier.size(5.dp))
            dropDownTextFiled(
                Modifier.weight(1f),
                financiers,
                label = "صاحب کالا",
                onItemSelected = {
                    owner = it?.toString() ?: ""
                },
                enabled = financiers.size > 1,
            )
        }
        Spacer(Modifier.size(5.dp))
        Button({},modifier = Modifier.align(Alignment.End),){
            Text("جستجو")
        }
    }
}
