package domain.trade.repository

import domain.trade.model.ProductTrade
import domain.trade.model.TradeType
import kotlinx.coroutines.flow.Flow

interface TradeRepository {
    suspend fun getTrades(date: String, type: TradeType): Flow<List<ProductTrade>>

    suspend fun addTrade(productTrade: ProductTrade)

    suspend fun deleteTrade(id: Int)
}