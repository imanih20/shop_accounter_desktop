package domain.statistic.usecase

import domain.statistic.model.Statistic
import domain.statistic.repository.StatisticRepository
import kotlinx.coroutines.flow.Flow

class GetStatisticsUseCase(private val rep: StatisticRepository) {
    suspend operator fun invoke(financier: String): Flow<List<Statistic>> {
        return rep.getStatistics(financier)
    }
}