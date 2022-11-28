package common.utils

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.TransactionManager

object DbUtils {
    fun connect(){
        Database.connect("jdbc:sqlite:/data/data.db", "org.sqlite.JDBC")
        // https://github.com/JetBrains/Exposed/wiki/FAQ
    }
}