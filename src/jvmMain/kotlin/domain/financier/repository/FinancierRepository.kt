package domain.financier.repository

import domain.financier.model.Financier
import kotlinx.coroutines.flow.Flow

interface FinancierRepository {
    suspend fun addFinancier(financier: Financier) : Flow<Boolean>

    suspend fun getFinancier(name: String) : Flow<Financier?>

    suspend fun getFinancierId(name: String) : Flow<Int>

    suspend fun getAllFinanciers() : Flow<List<Financier>>

    suspend fun editFinancier(financier: Financier)

    suspend fun deleteFinancier(id: Int)
}