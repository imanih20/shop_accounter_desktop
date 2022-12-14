package data.statistic.model

import data.financier.model.FinancierEntity
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object StatisticEntity : IntIdTable(){
    val financier  = varchar("financier",100).references(FinancierEntity.name, onUpdate = ReferenceOption.CASCADE, onDelete = ReferenceOption.CASCADE)
    val year = integer("year")
    val month = integer("month")
    val totalPurchase = integer("total_purchase")
    val totalSale = integer("total_sale")
    val totalIncome = integer("total_income")
    val isPaid = bool("is_paid")
}