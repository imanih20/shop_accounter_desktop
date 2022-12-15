package peresentation.base.components.main.components.statistics.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.financier.model.Financier

@Composable
fun financierListView(financierList: List<Financier>, modifier: Modifier, onItemSelected: (Financier) -> Unit) {
    var selected by remember { mutableStateOf(financierList.firstOrNull()?.id ?: 0) }
    LazyColumn(modifier) {
        items(financierList) { financier ->
            if (selected == 0) selected = financier.id
            financierItem(financier, isSelected = selected == financier.id) {
                onItemSelected(financier)
                selected = financier.id
            }
            Spacer(Modifier.size(5.dp))
        }
    }
}