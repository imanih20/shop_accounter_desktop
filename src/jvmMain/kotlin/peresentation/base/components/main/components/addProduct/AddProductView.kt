package peresentation.base.components.main.components.addProduct

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import domain.financier.model.Financier
import domain.product.model.Product
import domain.trade.model.ProductTrade
import ir.huri.jcal.JalaliCalendar
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import peresentation.base.components.main.components.addProduct.components.addView
import peresentation.base.components.main.components.addProduct.components.showProductView
import peresentation.common.components.adaptiveLayout
fun <T> SnapshotStateList<T>.reAssign(list: List<T>) : SnapshotStateList<T>{
    this.removeAll(this)
    this.addAll(list)
    return this
}
@Composable
fun addProductView(){
    val controller : AddProductController by inject(AddProductController::class.java)
    val purchaseList = remember { mutableStateListOf<ProductTrade>() }
    val financierList = remember { mutableStateListOf<Financier>() }
    val scope = rememberCoroutineScope()
    suspend fun refreshPurchaseList() {
        controller.getAllPurchases(JalaliCalendar().toString()).collect{
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
    val onSaveButtonClicked = fun(product: Product,totalPruchasePrice: Int,date:  String) {
        scope.launch {
            controller.saveProduct(product,totalPruchasePrice,date)
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
            addView(m,financierList,onSaveButtonClicked)
        },{m ->
            showProductView(purchaseList,m){
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






