package data.financier.model

import org.jetbrains.exposed.dao.id.IntIdTable

object FinancierEntity : IntIdTable() {
    val name = varchar("f_name", 100).uniqueIndex()
    val description = varchar("description", 1000).default("")
    val share = integer("share")
}