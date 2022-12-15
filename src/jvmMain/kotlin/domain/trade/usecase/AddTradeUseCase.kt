package domain.trade.usecase

import domain.trade.model.ProductTrade
import domain.trade.repository.TradeRepository

class AddTradeUseCase(private val rep: TradeRepository) {
    suspend operator fun invoke(trade: ProductTrade) {
        rep.addTrade(trade)
    }
}