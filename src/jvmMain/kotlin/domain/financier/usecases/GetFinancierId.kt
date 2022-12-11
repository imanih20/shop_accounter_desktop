package domain.financier.usecases

import domain.financier.repository.FinancierRepository
import kotlinx.coroutines.flow.Flow

class GetFinancierId(private val rep: FinancierRepository) {
    suspend operator fun invoke(name: String) : Flow<Int> {
        return rep.getFinancierId(name)
    }
}