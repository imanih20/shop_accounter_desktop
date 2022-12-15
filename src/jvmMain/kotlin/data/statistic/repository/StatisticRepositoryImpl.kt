package data.statistic.repository

import data.statistic.model.StatisticEntity
import domain.statistic.model.Statistic
import domain.statistic.repository.StatisticRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class StatisticRepositoryImpl : StatisticRepository {
    override suspend fun getStatistics(financier: String): Flow<List<Statistic>> {
        return flow {
            val list = arrayListOf<Statistic>()
            transaction {
                list.addAll(Statistic.getListFromQuery(
                    StatisticEntity.select { StatisticEntity.financier eq financier }
                ))
            }
            emit(list)
        }
    }

    override suspend fun addStatistic(statistic: Statistic) {
        transaction {
            StatisticEntity.insert {
                it[financier] = statistic.financier
                it[year] = statistic.year
                it[month] = statistic.month
                it[totalPurchase] = statistic.totalPurchase
                it[totalSale] = statistic.totalSale
                it[totalIncome] = statistic.totalIncome
                it[isPaid] = statistic.isPaid
            }
        }
    }

    override suspend fun updateStatistic(statistic: Statistic) {
        transaction {
            StatisticEntity.update({ StatisticEntity.id eq statistic.id }) {
                with(SqlExpressionBuilder) {
                    it[totalPurchase] = totalPurchase + statistic.totalPurchase
                    it[totalSale] = totalSale + statistic.totalSale
                    it[totalIncome] = totalIncome + statistic.totalIncome
                }
            }
        }
    }

    private fun getStatistic(financier: String, year: Int, month: Int): Flow<Int> {
        return flow {
            var isExist = 0

            transaction {
                StatisticEntity.select { (StatisticEntity.year eq year) and (StatisticEntity.month eq month) and (StatisticEntity.financier eq financier) }
                    .forEach {
                        isExist = it[StatisticEntity.id].value
                    }
            }
            emit(isExist)
        }
    }

    override suspend fun saveStatistic(statistic: Statistic) {
        var id: Int
        getStatistic(statistic.financier, statistic.year, statistic.month).collect {
            id = it
            if (id == 0)
                addStatistic(statistic)
            else {
                statistic.id = id
                updateStatistic(statistic)
            }
        }
    }

    override suspend fun changeIsPaid(id: Int, isPaid: Boolean) {
        transaction {
            StatisticEntity.update({ StatisticEntity.id eq id }) {
                it[StatisticEntity.isPaid] = isPaid
            }
        }
    }
}