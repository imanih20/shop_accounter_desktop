package domain.trade.usecase

import domain.trade.model.ProductTrade
import domain.trade.model.TradeType
import domain.trade.repository.TradeRepository
import kotlinx.coroutines.flow.Flow

class GetAllSalesUseCase(private val rep : TradeRepository) {
    suspend operator fun invoke() : Flow<List<ProductTrade>>{
        return rep.getAllTrades(TradeType.SALE)
    }
}