package peresentation.base.components.main.components.statistics.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.statistic.model.Statistic

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun showStatisticView(
    statisticList: List<Statistic>,
    modifier: Modifier,
    updateStatistic: (Statistic, Boolean) -> Unit
) {
    LazyColumn(modifier) {
        stickyHeader {
            statisticHeader()
        }
        items(statisticList) { statistic ->
            statisticItem(statistic) { isPaid ->
                updateStatistic(statistic, isPaid)
            }
            Spacer(Modifier.size(5.dp))
        }
    }
}