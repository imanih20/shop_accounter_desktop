package peresentation.base.components.main.components.saleProduct

import domain.financier.model.Financier
import domain.financier.usecases.GetFinancierUseCase
import domain.product.model.Product
import domain.product.usecase.GetAllProductsUseCase
import domain.product.usecase.IncreaseProductQuantityUseCase
import domain.product.usecase.ReduceProductQuantityUseCase
import domain.statistic.model.Statistic
import domain.statistic.usecase.SaveStatisticUseCase
import domain.trade.model.ProductTrade
import domain.trade.usecase.AddTradeUseCase
import domain.trade.usecase.DeleteTradeUseCase
import domain.trade.usecase.GetSalesOfDayUseCase
import kotlinx.coroutines.flow.Flow

class SaleProductController(
    private val addTrade: AddTradeUseCase,
    private val getProducts: GetAllProductsUseCase,
    private val getSalesOfDay: GetSalesOfDayUseCase,
    private val saveStatistic: SaveStatisticUseCase,
    private val deleteTrade: DeleteTradeUseCase,
    private val getFinancier: GetFinancierUseCase,
    private val reduceProductQuantity: ReduceProductQuantityUseCase,
    private val increaseProductQuantity: IncreaseProductQuantityUseCase
) {
    suspend fun saveSale(trade: ProductTrade) {
        val dateFields = trade.date.split('-')
        var financier: Financier?
        addTrade(trade)
        getFinancier(trade.owner).collect {
            financier = it
            val share = trade.profit * (financier!!.share.toDouble() / 100)
            if (financier != null) saveStatistic(
                Statistic(
                    financier = trade.owner,
                    year = dateFields[0].toInt(),
                    month = dateFields[1].toInt(),
                    totalSale = trade.totalPrice,
                    totalIncome = share.toInt()
                )
            )
        }
        reduceProductQuantity(trade.title, trade.quantity)
    }

    suspend fun getAllProducts(): Flow<List<Product>> {
        return getProducts()
    }


    suspend fun getSales(date: String): Flow<List<ProductTrade>> {
        val dateFields = date.split('-')
        return getSalesOfDay(dateFields[0], dateFields[1], dateFields[2])
    }

    suspend fun deleteSale(trade: ProductTrade) {
        val dateFields = trade.date.split('-')
        deleteTrade(trade.id)
        getFinancier(trade.owner).collect {
            val financier = it
            val share = trade.profit * (financier!!.share.toDouble() / 100)
            saveStatistic(
                Statistic(
                    financier = trade.owner,
                    year = dateFields[0].toInt(),
                    month = dateFields[1].toInt(),
                    totalSale = -trade.totalPrice,
                    totalIncome = -share.toInt()
                )
            )
        }
        increaseProductQuantity(trade.title,trade.quantity)
    }
}