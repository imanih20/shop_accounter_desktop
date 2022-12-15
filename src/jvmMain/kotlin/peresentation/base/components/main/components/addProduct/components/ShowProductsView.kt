package peresentation.base.components.main.components.addProduct.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common.consts.SPACER_SIZE
import domain.trade.model.ProductTrade
import peresentation.common.components.deleteDialog

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun showProductView(
    purchaseList: List<ProductTrade>,
    modifier: Modifier = Modifier,
    onDateChanged: (String)->Unit,
    onDeleteItemRequest: (Int) -> Unit
) {
    var id by remember { mutableStateOf(0) }
    var visible by remember { mutableStateOf(false) }
    Box(modifier) {
        if (visible) deleteDialog({ visible = false }) {
            onDeleteItemRequest(id)
            visible = false
        }
        val state = rememberLazyListState()
        LazyColumn(
            state = state
        ) {
            stickyHeader {
                showListHeader(){
                    onDateChanged(it)
                }
            }
            items(purchaseList) {
                showListItem(it) { i ->
                    visible = true
                    id = i
                }
                Spacer(Modifier.size(SPACER_SIZE))
            }
        }
        VerticalScrollbar(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxHeight(),
            adapter = rememberScrollbarAdapter(
                scrollState = state
            )
        )
    }
}

