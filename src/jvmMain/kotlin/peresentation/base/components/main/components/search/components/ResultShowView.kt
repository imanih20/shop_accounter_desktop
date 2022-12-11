package peresentation.base.components.main.components.search.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.financier.model.Financier
import domain.product.model.Product
import peresentation.common.components.deleteDialog


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun resultShowView(
    modifier: Modifier = Modifier,
    list: List<Product>,
    financiers: List<Financier>,
    onDeleteRequest: (Int)->Unit,
    onEditRequest: (Product)->Unit
    ) {
    var product by remember { mutableStateOf<Product?>(null) }
    var id by remember { mutableStateOf(0) }
    var isEditActivate by remember { mutableStateOf(false) }
    var isDeleteActivate by remember { mutableStateOf(false) }
    Box(modifier){
        if (isEditActivate) editProductDialog(
            {isEditActivate = false},
            product!!,
            financiers,
            {
                if (it!=null){
                    onEditRequest(it)
                }
                isEditActivate = false
            }
        )
        if (isDeleteActivate) deleteDialog(
            {isDeleteActivate = false},
            {
                onDeleteRequest(id)
                isDeleteActivate = false
            }
        )
        LazyColumn(Modifier.padding(10.dp)) {
            stickyHeader {
                resultListHeader()
            }
            items(list){
                resultItemView(
                    product = it,
                    onEditClicked = {pr->
                        product = pr
                        isEditActivate = true
                    },
                    onDeleteClicked = {i->
                        id = i
                        isDeleteActivate = true
                    }
                )
                Spacer(Modifier.size(5.dp))
            }
        }
    }
}
