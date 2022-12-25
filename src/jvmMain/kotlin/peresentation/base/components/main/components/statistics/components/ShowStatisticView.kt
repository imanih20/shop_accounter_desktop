package peresentation.base.components.main.components.statistics.components

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
import domain.statistic.model.Statistic

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun showStatisticView(
    statisticList: List<Statistic>,
    modifier: Modifier,
    updateStatistic: (Statistic, Boolean) -> Unit
) {
    Box(modifier){
        val state = rememberLazyListState()
        LazyColumn(Modifier.padding(10.dp),state = state) {
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