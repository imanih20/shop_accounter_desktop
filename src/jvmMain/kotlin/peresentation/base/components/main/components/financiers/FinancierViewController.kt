package peresentation.base.components.main.components.financiers

import domain.financier.model.Financier
import domain.financier.usecases.AddFinancierUseCase
import domain.financier.usecases.DeleteFinancierUseCase
import domain.financier.usecases.EditFinancierUseCase
import domain.financier.usecases.GetAllFinanciersUseCase
import kotlinx.coroutines.flow.Flow

class FinancierViewController(
    private val addFinancier: AddFinancierUseCase,
    private val getAllFinanciers: GetAllFinanciersUseCase,
    private val editFinancier: EditFinancierUseCase,
    private val delFinancier: DeleteFinancierUseCase
) {

    suspend fun saveFinancier(financier: Financier):Flow<Boolean>{
        return addFinancier(financier)
    }
    suspend fun getFinanciers() : Flow<List<Financier>>{
        return getAllFinanciers()
    }

    suspend fun updateFinancier(financier: Financier){
        editFinancier(financier)
    }

    suspend fun deleteFinancier(id: Int){
        delFinancier(id)
    }
}