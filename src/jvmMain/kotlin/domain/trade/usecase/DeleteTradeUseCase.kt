package domain.trade.usecase

import domain.trade.repository.TradeRepository

class DeleteTradeUseCase(private val rep: TradeRepository) {
    suspend operator fun invoke(id: Int) {
        return rep.deleteTrade(id)
    }
}