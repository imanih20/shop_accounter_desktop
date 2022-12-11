package data.product.model

import data.financier.model.FinancierEntity
import org.jetbrains.exposed.dao.id.IntIdTable

object ProductEntity : IntIdTable(){
    val title = varchar("title",100).uniqueIndex()
    val quantity = double("quantity")
    val purchasePrice = integer("purchase_price")
    val salePrice = integer("sale_price")
    val owner = varchar("owner",100).references(FinancierEntity.name)
}