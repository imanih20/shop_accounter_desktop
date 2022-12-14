package peresentation.base.components.main.components.statistics

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import domain.financier.model.Financier
import domain.statistic.model.Statistic
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import peresentation.base.components.main.components.addProduct.reAssign
import peresentation.base.components.main.components.statistics.components.financierListView
import peresentation.base.components.main.components.statistics.components.showStatisticView
import peresentation.common.components.adaptiveLayout

@Composable
fun statisticsView(){
    val controller : StatisticsController by inject(StatisticsController::class.java)
    val financierList = remember { mutableStateListOf<Financier>()}
    val statisticList = remember { mutableStateListOf<Statistic>() }
    val scope = rememberCoroutineScope()
    suspend fun refreshStatisticList(financier: String){
        controller.getFinStatistics(financier).collect {
            statisticList.reAssign(it).sortBy { st->
                st.month
            }
            statisticList.sortBy { st->
                st.year
            }
        }
    }
    scope.launch {
        controller.getFinanciers().collect{
            financierList.reAssign(it)
        }
        if (financierList.isNotEmpty()){
            refreshStatisticList(financierList.first().name)
        }
    }

    adaptiveLayout(
        {modifier->
            financierListView(financierList,modifier){
                scope.launch {
                    refreshStatisticList(it.name)
                }
            }
        },
        {
            showStatisticView(statisticList,it){statistic,isPaid->
                scope.launch {
                    controller.updateIsPaid(statistic.id,isPaid)
                    refreshStatisticList(statistic.financier)
                }
            }
        }
    )

}

