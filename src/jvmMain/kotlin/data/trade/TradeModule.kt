package data.trade

import data.trade.repository.TradeRepositoryImpl
import domain.trade.repository.TradeRepository
import domain.trade.usecase.AddTradeUseCase
import domain.trade.usecase.DeleteTradeUseCase
import domain.trade.usecase.GetPurchaseOfDayUseCase
import domain.trade.usecase.GetSalesOfDayUseCase
import org.koin.dsl.module

val tradeModule = module {
    single<TradeRepository> {
        TradeRepositoryImpl()
    }

    single { AddTradeUseCase(get()) }

    single { DeleteTradeUseCase(get()) }

    single { GetPurchaseOfDayUseCase(get()) }

    single { GetSalesOfDayUseCase(get()) }

}