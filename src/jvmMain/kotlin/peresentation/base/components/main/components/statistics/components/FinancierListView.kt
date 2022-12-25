package peresentation.base.components.main.components.statistics.components

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
import domain.financier.model.Financier

@Composable
fun financierListView(financierList: List<Financier>, modifier: Modifier, onItemSelected: (Financier) -> Unit) {
    var selected by remember { mutableStateOf(financierList.firstOrNull()?.id ?: 0) }
    Box(modifier){
        val state = rememberLazyListState()
        LazyColumn(Modifier.padding(10.dp),state = state) {
            items(financierList) { financier ->
                if (selected == 0) selected = financier.id
                financierItem(financier, isSelected = selected == financier.id) {
                    onItemSelected(financier)
                    selected = financier.id
                }
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