package domain.financier.usecases

import domain.financier.model.Financier
import domain.financier.repository.FinancierRepository
import kotlinx.coroutines.flow.Flow

class GetFinancierUseCase(private val rep: FinancierRepository) {
    suspend operator fun invoke(name: String): Flow<Financier?> {
        return rep.getFinancier(name)
    }
}