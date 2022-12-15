package data.financier

import data.financier.repository.FinancierRepositoryImpl
import domain.financier.repository.FinancierRepository
import domain.financier.usecases.*
import org.koin.dsl.module

val financierModule = module {
    single<FinancierRepository> { FinancierRepositoryImpl() }

    single { AddFinancierUseCase(get()) }

    single { DeleteFinancierUseCase(get()) }

    single { EditFinancierUseCase(get()) }

    single { GetFinancierUseCase(get()) }

    single { GetAllFinanciersUseCase(get()) }
}