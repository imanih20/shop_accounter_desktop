package domain.statistic.model

import data.statistic.model.StatisticEntity
import domain.financier.model.Financier
import domain.product.model.Product
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.ResultRow

data class Statistic(
    var id: Int = 0,
    val financier: String,
    val year: Int,
    val month: Int,
    val totalPurchase: Int = 0,
    val totalSale: Int = 0,
    val totalIncome: Int = 0,
    val isPaid: Boolean = false
) {
    companion object{
        fun getFromResult(result: ResultRow) : Statistic {
            return Statistic(
                result[StatisticEntity.id].value,
                result[StatisticEntity.financier],
                result[StatisticEntity.year],
                result[StatisticEntity.month],
                result[StatisticEntity.totalPurchase],
                result[StatisticEntity.totalSale],
                result[StatisticEntity.totalIncome],
                result[StatisticEntity.isPaid]
            )
        }

        fun getListFromQuery(query: Query) : List<Statistic>{
            val list = arrayListOf<Statistic>()
            query.forEach {
                list.add(
                    Statistic.getFromResult(it)
                )
            }
            return list
        }
    }
}
