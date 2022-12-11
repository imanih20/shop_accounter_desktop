package domain.trade.repository

import domain.trade.model.ProductTrade
import domain.trade.model.TradeType
import kotlinx.coroutines.flow.Flow

interface TradeRepository {
    suspend fun getTrade(id: Int) : Flow<ProductTrade?>

    suspend fun getTrades(date: String,type: TradeType) : Flow<List<ProductTrade>>

    suspend fun getAllTrades(type: TradeType) : Flow<List<ProductTrade>>

    suspend fun addTrade(productTrade: ProductTrade)

    suspend fun deleteTrade(id: Int)
}