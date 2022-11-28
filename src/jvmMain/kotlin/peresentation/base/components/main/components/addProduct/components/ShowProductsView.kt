package peresentation.base.components.main.components.addProduct.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import peresentation.base.components.main.components.addProduct.AddProductController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun showProductView(controller: AddProductController, modifier: Modifier = Modifier) {
    Box(modifier) {
        val state = rememberLazyListState()
        LazyColumn(
            state = state
        ) {
            stickyHeader {
                showListHeader()
            }
            items(controller.getAllProducts()){
                showListItem(it)
                Spacer(Modifier.size(5.dp))
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