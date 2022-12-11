package domain.trade.usecase

import domain.trade.model.ProductTrade
import domain.trade.repository.TradeRepository
import kotlinx.coroutines.flow.Flow

class GetTradeUseCase(private val rep : TradeRepository) {
    suspend operator fun invoke(id: Int) : Flow<ProductTrade?>{
        return rep.getTrade(id)
    }
}