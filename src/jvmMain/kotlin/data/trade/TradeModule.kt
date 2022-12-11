package data.trade

import data.trade.repository.TradeRepositoryImpl
import domain.trade.repository.TradeRepository
import domain.trade.usecase.*
import org.koin.dsl.module

val tradeModule = module {
    single<TradeRepository> {
        TradeRepositoryImpl()
    }

    single { AddTradeUseCase(get()) }

    single {DeleteTradeUseCase(get())}

    single { GetAllPurchasesUseCase(get()) }

    single { GetAllSalesUseCase(get()) }

    single { GetPurchaseOfDayUseCase(get()) }

    single { GetPurchaseOfMonthUseCase(get()) }

    single { GetPurchaseOfYearUseCase(get()) }

    single { GetSalesOfDayUseCase(get()) }

    single { GetSalesOfMonthUseCase(get()) }

    single { GetSalesOfYearUseCase(get()) }

    single { GetTradeUseCase(get()) }
}