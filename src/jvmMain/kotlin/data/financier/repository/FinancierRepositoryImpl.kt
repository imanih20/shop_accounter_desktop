package data.financier.repository

import data.financier.model.FinancierEntity
import domain.financier.model.Financier
import domain.financier.repository.FinancierRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class FinancierRepositoryImpl : FinancierRepository {
    override suspend fun addFinancier(financier: Financier) : Flow<Boolean>{
        return flow {
            var isSucceed = false
            try {
                transaction {
                    val id = FinancierEntity.insertAndGetId {
                        it[name] = financier.name
                        it[description] = financier.description
                        it[share] = financier.share
                    }.value
                    isSucceed = id > 0
                }
            }catch (e: ExposedSQLException){
                isSucceed = false
            }
            emit(isSucceed)
        }
    }

    override suspend fun getFinancier(name: String): Flow<Financier?> {
        return flow {
            var financier : Financier? = null
            transaction {
                FinancierEntity.select { FinancierEntity.name eq name }.forEach {
                    financier = Financier.getFinancierFromResult(it)
                }
            }
            emit(financier)
        }
    }

    override suspend fun getFinancierId(name: String): Flow<Int> {
        return flow {
            var id = 0
            transaction {
                FinancierEntity.select { FinancierEntity.name eq name }.forEach {
                    id = it[FinancierEntity.id].value
                }
            }
            emit(id)
        }
    }

    override suspend fun getAllFinanciers(): Flow<List<Financier>> {
        return flow {
            val list = arrayListOf<Financier>()
            transaction {
                FinancierEntity.selectAll().forEach {
                    list.add(Financier.getFinancierFromResult(it))
                }
            }
            emit(list)
        }
    }

    override suspend fun editFinancier(financier: Financier) {
        transaction {
            FinancierEntity.update({FinancierEntity.id eq financier.id}) {
                it[name] =  financier.name
                it[description] = financier.description
                it[share] = financier.share
            }
        }
    }

    override suspend fun deleteFinancier(id: Int) {
        transaction {
            FinancierEntity.deleteWhere { FinancierEntity.id eq id}
        }
    }
}