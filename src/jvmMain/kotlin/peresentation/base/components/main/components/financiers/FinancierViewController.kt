package peresentation.base.components.main.components.financiers

import domain.financier.model.Financier

class FinancierViewController {
    fun getFinanciers() : List<Financier>{
        return arrayListOf(Financier(name="علی", description = "فروشنده ساندویچ"))
    }
}