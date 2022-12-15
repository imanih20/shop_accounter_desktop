package domain.statistic.usecase

import domain.statistic.repository.StatisticRepository

class UpdateIsPaidUseCase(private val rep: StatisticRepository) {
    suspend operator fun invoke(id: Int, isPaid: Boolean) {
        rep.changeIsPaid(id, isPaid)
    }
}