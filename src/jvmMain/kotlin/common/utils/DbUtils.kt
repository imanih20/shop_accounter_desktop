package common.utils

import data.financier.model.FinancierEntity
import data.product.model.ProductEntity
import data.statistic.model.StatisticEntity
import data.trade.model.TradeEntity
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

object DbUtils {
    fun configureDb() {
        Database.connect("jdbc:h2:./myh2file", "org.h2.Driver")        // https://github.com/JetBrains/Exposed/wiki/FAQ
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(FinancierEntity, ProductEntity, TradeEntity, StatisticEntity)
        }
    }
}