package domain.sale.repository

import domain.sale.model.Sale

interface SaleRepository {
    suspend fun getSales(date: String) : List<Sale>

    suspend fun getAllSales() : List<Sale>

    suspend fun addSale(sale: Sale)

    suspend fun deleteSale(id: String)
}