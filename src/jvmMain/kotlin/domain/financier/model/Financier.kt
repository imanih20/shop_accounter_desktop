package domain.financier.model

import data.financier.model.FinancierEntity
import org.jetbrains.exposed.sql.ResultRow

data class Financier(val id :Int = 0,val name: String = "",val description: String = "",val share: Int){
    companion object{
        fun getFinancierFromResult(result: ResultRow) : Financier{
            return Financier(
                result[FinancierEntity.id].value,
                result[FinancierEntity.name],
                result[FinancierEntity.description],
                result[FinancierEntity.share]
            )
        }
    }

    override fun toString(): String {
        return name
    }
}