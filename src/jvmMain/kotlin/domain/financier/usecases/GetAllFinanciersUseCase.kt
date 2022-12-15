package domain.financier.usecases

import domain.financier.model.Financier
import domain.financier.repository.FinancierRepository
import kotlinx.coroutines.flow.Flow

class GetAllFinanciersUseCase(private val rep: FinancierRepository) {
    suspend operator fun invoke(): Flow<List<Financier>> {
        return rep.getAllFinanciers()
    }
}