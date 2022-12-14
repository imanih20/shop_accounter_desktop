package data.trade.model

import data.financier.model.FinancierEntity
import data.product.model.ProductEntity
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object TradeEntity : IntIdTable(){
    val title = varchar("title",100).references(ProductEntity.title,onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    val type = varchar("type",100)
    val quantity = double("quantity")
    val totalPrice = integer("total_price")
    val profit = integer("profit")
    val owner = varchar("owner",100).references(ProductEntity.owner, onUpdate = ReferenceOption.CASCADE, onDelete = ReferenceOption.CASCADE)
    val date = varchar("date",15)
}