package peresentation.base.components.main.components.addProduct

import common.utils.Controller
import domain.financier.model.Financier
import domain.financier.usecases.GetAllFinanciersUseCase
import domain.product.model.Product
import domain.product.usecase.AddProductUseCase
import domain.product.usecase.DeleteProductUseCase
import domain.statistic.model.Statistic
import domain.statistic.usecase.SaveStatisticUseCase
import domain.trade.model.ProductTrade
import domain.trade.model.TradeType
import domain.trade.usecase.AddTradeUseCase
import domain.trade.usecase.DeleteTradeUseCase
import domain.trade.usecase.GetPurchaseOfDayUseCase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AddProductController(
    private val addProduct: AddProductUseCase,
    private val getAllPurchasesOfDay: GetPurchaseOfDayUseCase,
    private val addTrade: AddTradeUseCase,
    private val saveStatistic: SaveStatisticUseCase,
    private val getAllFinancier: GetAllFinanciersUseCase,
    private val deleteTradeById: DeleteTradeUseCase
) : Controller {
    suspend fun saveProduct(product: Product,totalPurchasePrice: Int,date: String){
        val dateFields = date.split("-")
        coroutineScope {
            launch {
                addProduct(product)
            }
            launch {
                addTrade(
                    trade = ProductTrade(
                        title = product.title,
                        type = TradeType.PURCHASE,
                        quantity = product.quantity,
                        totalPrice = totalPurchasePrice.toInt(),
                        date = date,
                        profit = 0,
                        owner = product.owner
                    )
                )
            }
            launch {
                saveStatistic(
                    Statistic(
                        financier = product.owner,
                        year = dateFields[0].toInt(),
                        month = dateFields[1].toInt(),
                        totalPurchase = totalPurchasePrice.toInt(),
                        totalSale = 0,
                        totalIncome = 0,
                    )
                )

            }
        }
    }
    suspend fun getAllPurchases(date: String) : Flow<List<ProductTrade>> {
        val dateFields = date.split('-')
        return getAllPurchasesOfDay(dateFields[0],dateFields[1],dateFields[2])
    }
    suspend fun getFinanciers() : Flow<List<Financier>> {
        return getAllFinancier()
    }

    suspend fun deleteTrade(id: Int){
        deleteTradeById(id)
    }
}