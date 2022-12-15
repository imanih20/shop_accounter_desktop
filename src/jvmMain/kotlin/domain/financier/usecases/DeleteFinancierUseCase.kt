package domain.financier.usecases

import domain.financier.repository.FinancierRepository

class DeleteFinancierUseCase(private val rep: FinancierRepository) {
    suspend operator fun invoke(id: Int) {
        rep.deleteFinancier(id)
    }
}