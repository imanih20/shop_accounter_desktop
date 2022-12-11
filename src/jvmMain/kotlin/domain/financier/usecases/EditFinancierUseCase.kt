package domain.financier.usecases

import domain.financier.model.Financier
import domain.financier.repository.FinancierRepository

class EditFinancierUseCase(private val rep: FinancierRepository) {
    suspend operator fun invoke(financier: Financier){
        rep.editFinancier(financier)
    }
}