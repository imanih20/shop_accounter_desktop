package domain.trade.usecase

import domain.trade.model.ProductTrade
import domain.trade.model.TradeType
import domain.trade.repository.TradeRepository
import kotlinx.coroutines.flow.Flow

class GetPurchaseOfYearUseCase (private val rep: TradeRepository){
    suspend operator fun invoke(year: String) : Flow<List<ProductTrade>> {
        return rep.getTrades("$year-%",TradeType.PURCHASE)
    }
}