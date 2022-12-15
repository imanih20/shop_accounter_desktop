package peresentation.base.components.main.components.addProduct

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import domain.financier.model.Financier
import domain.product.model.Product
import domain.trade.model.ProductTrade
import ir.huri.jcal.JalaliCalendar
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import peresentation.base.components.main.components.addProduct.components.addView
import peresentation.base.components.main.components.addProduct.components.showProductView
import peresentation.common.components.adaptiveLayout

fun <T> SnapshotStateList<T>.reAssign(list: List<T>): SnapshotStateList<T> {
    this.removeAll(this)
    this.addAll(list)
    return this
}

@Composable
fun addProductView() {
    val controller: AddProductController by inject(AddProductController::class.java)
    val purchaseList = remember { mutableStateListOf<ProductTrade>() }
    val financierList = remember { mutableStateListOf<Financier>() }
    var date by remember { mutableStateOf(JalaliCalendar().toString()) }
    val scope = rememberCoroutineScope()
    suspend fun refreshPurchaseList() {
        controller.getAllPurchases(date).collect {
            purchaseList.reAssign(it)
        }
    }
    scope.launch {
        refreshPurchaseList()
        launch {
            controller.getFinanciers().collect {
                financierList.reAssign(it)
            }
        }
    }
    val onSaveButtonClicked = fun(product: Product, totalPruchasePrice: Int, date: String) {
        scope.launch {
            controller.saveProduct(product, totalPruchasePrice, date)
            refreshPurchaseList()
        }
    }
//    if (LocalWindowSize.current==WindowSize.EXPAND)Row(Modifier.padding(20.dp)) {
//        addView(controller,Modifier.fillMaxWidth(0.3f))
//        Spacer(Modifier.size(15.dp))
//        showProductView(controller,Modifier.fillMaxWidth())
//    } else {
//        Column(Modifier.padding(20.dp)) {
//            addView(controller,Modifier.fillMaxWidth())
//            Spacer(Modifier.size(15.dp))
//            showProductView(controller,Modifier.fillMaxWidth())
//        }
//    }
    adaptiveLayout(
        { m ->
            addView(m, financierList, onSaveButtonClicked)
        }, { m ->
            showProductView(
                purchaseList,
                m,{
                    date = it
                    scope.launch {
                        refreshPurchaseList()
                    }
                }) {
                scope.launch {
                    controller.deleteTrade(it)
                }
                scope.launch {
                    refreshPurchaseList()
                }
            }
        }
    )
}






