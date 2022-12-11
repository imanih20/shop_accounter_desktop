package domain.financier.usecases

import domain.financier.model.Financier
import domain.financier.repository.FinancierRepository
import kotlinx.coroutines.flow.Flow

class AddFinancierUseCase(private val rep: FinancierRepository) {
    suspend operator fun invoke(financier: Financier) : Flow<Boolean>{
        return rep.addFinancier(financier)
    }
}