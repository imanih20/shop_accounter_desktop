package domain.trade.model

import data.trade.model.TradeEntity
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.ResultRow

enum class TradeType {
    SALE, PURCHASE, NOTHING;

    companion object {
        fun getFromString(type: String): TradeType {
            return when (type) {
                "sale" -> SALE
                "purchase" -> PURCHASE
                else -> NOTHING
            }
        }

        fun getString(type: TradeType): String {
            return when (type) {
                SALE -> "sale"
                PURCHASE -> "purchase"
                else -> ""
            }
        }

    }
}

data class ProductTrade(
    val id: Int = 0,
    val title: String,
    val type: TradeType,
    val quantity: Double,
    val totalPrice: Int,
    val profit: Int,
    val date: String,
    val owner: String
) {
    companion object {
        fun getTradeFromResult(result: ResultRow): ProductTrade {
            return ProductTrade(
                result[TradeEntity.id].value,
                result[TradeEntity.title],
                TradeType.getFromString(result[TradeEntity.type]),
                result[TradeEntity.quantity],
                result[TradeEntity.totalPrice],
                result[TradeEntity.profit],
                result[TradeEntity.date],
                result[TradeEntity.owner]
            )
        }

        fun getTradeFromQuery(query: Query): List<ProductTrade> {
            val list = arrayListOf<ProductTrade>()
            query.forEach {
                list.add(
                    getTradeFromResult(it)
                )
            }
            return list
        }
    }
}