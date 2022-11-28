package peresentation.base.components.main.components.saleProduct.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import peresentation.base.components.main.components.saleProduct.SaleProductController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun showView(controller: SaleProductController, modifier: Modifier = Modifier) {
    Box(modifier) {
        val state = rememberLazyListState()
        LazyColumn(
            state = state
        ) {
            stickyHeader {
                saleListHeader()
            }
            items(controller.getSales()){
                listItem(it.pid,it.quantity.toString(),it.totalPrice.toString(),it.date)
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