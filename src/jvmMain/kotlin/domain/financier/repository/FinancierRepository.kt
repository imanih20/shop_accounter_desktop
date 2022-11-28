package domain.financier.repository

import domain.financier.model.Financier

interface FinancierRepository {
    suspend fun addFinancier(financier: Financier)

    suspend fun getAllFinanciers() : List<Financier>

    suspend fun editFinancier(financier: Financier)

    suspend fun deleteFinancier(id: String)
}