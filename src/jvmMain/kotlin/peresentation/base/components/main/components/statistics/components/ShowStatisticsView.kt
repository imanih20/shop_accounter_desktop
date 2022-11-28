package peresentation.base.components.main.components.statistics.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import peresentation.base.components.main.components.statistics.StatisticsController

@Composable
fun showStatisticsView(controller: StatisticsController, modifier: Modifier) {
    val state = rememberScrollState()
    val scrollbarAdapter = rememberScrollbarAdapter(state)
    Box(modifier){
        Column(Modifier.verticalScroll(state)) {
            statisticView(
                "خرید",
                102000,
                24000000,
                250000000
            )
            Spacer(Modifier.size(10.dp))
            statisticView(
                "فروش",
                102000,
                24000000,
                250000000
            )
            Spacer(Modifier.size(10.dp))
            statisticView(
                "سود",
                102000,
                24000000,
                250000000
            )
        }
        VerticalScrollbar(scrollbarAdapter,Modifier.align(Alignment.CenterEnd).fillMaxHeight())
    }
}



