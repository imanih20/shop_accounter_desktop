package data.trade.repository

import data.trade.model.TradeEntity
import domain.trade.model.ProductTrade
import domain.trade.model.TradeType
import domain.trade.repository.TradeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class TradeRepositoryImpl : TradeRepository {
    override suspend fun getTrades(date: String, type: TradeType): Flow<List<ProductTrade>> {
        return flow {
            val list = arrayListOf<ProductTrade>()
            transaction {
                list.addAll(
                    ProductTrade.getTradeFromQuery(
                        TradeEntity.select {
                            (TradeEntity.date eq date) and (TradeEntity.type eq TradeType.getString(
                                type
                            ))
                        }
                    )
                )
            }
            emit(list)
        }
    }


    override suspend fun addTrade(productTrade: ProductTrade) {
        transaction {
            TradeEntity.insert {
                it[title] = productTrade.title
                it[type] = TradeType.getString(productTrade.type)
                it[quantity] = productTrade.quantity
                it[totalPrice] = productTrade.totalPrice
                it[profit] = productTrade.profit
                it[date] = productTrade.date
                it[owner] = productTrade.owner
            }
        }
    }

    override suspend fun deleteTrade(id: Int) {
        transaction {
            TradeEntity.deleteWhere { TradeEntity.id eq id }
        }
    }
}