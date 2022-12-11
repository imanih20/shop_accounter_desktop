package data.statistic

import data.statistic.repository.StatisticRepositoryImpl
import domain.statistic.repository.StatisticRepository
import domain.statistic.usecase.SaveStatisticUseCase
import domain.statistic.usecase.GetStatisticsUseCase
import domain.statistic.usecase.UpdateIsPaidUseCase
import org.koin.dsl.module

val statisticModule = module {
    single<StatisticRepository> { StatisticRepositoryImpl() }

    single { SaveStatisticUseCase(get()) }

    single { GetStatisticsUseCase(get()) }

    single { UpdateIsPaidUseCase(get()) }
}