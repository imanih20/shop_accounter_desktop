package domain.trade.usecase

import domain.trade.model.ProductTrade
import domain.trade.model.TradeType
import domain.trade.repository.TradeRepository
import kotlinx.coroutines.flow.Flow

class GetPurchaseOfMonthUseCase(private val rep : TradeRepository) {
    suspend operator fun invoke(year: String, month: String) : Flow<List<ProductTrade>> {
        return rep.getTrades("$year-$month-%",TradeType.PURCHASE)
    }
}