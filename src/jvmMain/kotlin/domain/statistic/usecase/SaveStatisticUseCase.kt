package domain.statistic.usecase

import domain.statistic.model.Statistic
import domain.statistic.repository.StatisticRepository

class SaveStatisticUseCase(private val rep: StatisticRepository) {
    suspend operator fun invoke(statistic: Statistic) {
        rep.saveStatistic(statistic)
    }
}