package data.statistic.model

import data.financier.model.FinancierEntity
import org.jetbrains.exposed.dao.id.IntIdTable

object StatisticEntity : IntIdTable(){
    val financier  = varchar("financier",100).references(FinancierEntity.name)
    val year = integer("year")
    val month = integer("month")
    val totalPurchase = integer("total_purchase")
    val totalSale = integer("total_sale")
    val totalIncome = integer("total_income")
    val isPaid = bool("is_paid")
}