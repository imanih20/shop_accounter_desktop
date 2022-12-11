package data.financier.model

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table

object FinancierEntity : IntIdTable(){
    val name = varchar("name",100).uniqueIndex()
    val description = varchar("description",1000).default("")
    val share = integer("share")
}