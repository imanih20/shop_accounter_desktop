package peresentation.base.components.main.components.saleProduct.components

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
import androidx.compose.ui.unit.dp
import domain.trade.model.ProductTrade
import peresentation.common.components.deleteDialog

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun showView(saleList: List<ProductTrade>, modifier: Modifier = Modifier,onDateChanged: (String)->Unit, onDeleteRequest: (ProductTrade) -> Unit) {
    var visible by remember { mutableStateOf(false) }
    var trade by remember { mutableStateOf<ProductTrade?>(null) }
    Box(modifier) {
        val state = rememberLazyListState()
        if (visible) deleteDialog({ visible = false }) {
            trade?.let{onDeleteRequest(it)}
            visible = false
        }
        LazyColumn(
            state = state
        ) {
            stickyHeader {
                saleListHeader{
                    onDateChanged(it)
                }
            }
            items(saleList) {

                listItem(it) { tr ->
                    trade = tr
                    visible = true
                }
                Spacer(Modifier.size(2.dp))
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