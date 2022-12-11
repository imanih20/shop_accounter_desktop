package domain.statistic.repository

import domain.financier.model.Financier
import domain.statistic.model.Statistic
import kotlinx.coroutines.flow.Flow

interface StatisticRepository {
    suspend fun getStatistics(financier: String) : Flow<List<Statistic>>

    suspend fun addStatistic(statistic: Statistic)

    suspend fun updateStatistic(statistic: Statistic)

    suspend fun saveStatistic(statistic: Statistic)

    suspend fun changeIsPaid(id: Int,isPaid: Boolean)
}