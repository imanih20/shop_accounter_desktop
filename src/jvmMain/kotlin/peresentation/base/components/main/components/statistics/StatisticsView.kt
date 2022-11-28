package peresentation.base.components.main.components.statistics

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import peresentation.base.components.main.components.statistics.components.showStatisticsView
import peresentation.base.components.main.components.statistics.components.timeFrameView
import peresentation.common.components.adaptiveLayout

@Composable
fun statisticsView(){
    val controller = StatisticsController()
    adaptiveLayout(
        {
            timeFrameView(controller,it)
        },
        {
            showStatisticsView(controller,it)
        }
    )

}

