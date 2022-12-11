package peresentation.base.components.main.components.statistics

import common.utils.Controller
import domain.financier.model.Financier
import domain.financier.usecases.GetAllFinanciersUseCase
import domain.statistic.model.Statistic
import domain.statistic.usecase.GetStatisticsUseCase
import domain.statistic.usecase.SaveStatisticUseCase
import domain.statistic.usecase.UpdateIsPaidUseCase
import kotlinx.coroutines.flow.Flow

class StatisticsController(
    private val getAllFinanciers: GetAllFinanciersUseCase,
    private val getStatistics: GetStatisticsUseCase,
    private val changeIsPaid: UpdateIsPaidUseCase
) : Controller {
    suspend fun getFinanciers() : Flow<List<Financier>>{
        return getAllFinanciers()
    }

    suspend fun getFinStatistics(financier: String) : Flow<List<Statistic>>{
        return getStatistics(financier)
    }

    suspend fun updateIsPaid(id: Int, isPaid: Boolean) {
        changeIsPaid(id,isPaid)
    }
}