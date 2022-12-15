package peresentation.base.components.main.components.saleProduct

import androidx.compose.runtime.*
import domain.product.model.Product
import domain.trade.model.ProductTrade
import ir.huri.jcal.JalaliCalendar
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import peresentation.base.components.main.components.addProduct.reAssign
import peresentation.base.components.main.components.saleProduct.components.saleView
import peresentation.base.components.main.components.saleProduct.components.showView
import peresentation.common.components.adaptiveLayout

@Composable
fun saleProductView() {
    val controller: SaleProductController by inject(SaleProductController::class.java)
    val scope = rememberCoroutineScope()
    val productList = remember { mutableStateListOf<Product>() }
    val saleList = remember { mutableStateListOf<ProductTrade>() }
    var date by remember { mutableStateOf(JalaliCalendar().toString()) }
    suspend fun refreshSaleList() {
        controller.getSales(date).collect {
            saleList.reAssign(it)
        }
    }
    scope.launch {
        controller.getAllProducts().collect {
            productList.reAssign(it)
        }
        refreshSaleList()
    }
//    if (LocalWindowSize.current == WindowSize.EXPAND)
//        Row (Modifier.padding(20.dp)){
//            saleView(controller,Modifier.fillMaxWidth(0.3f))
//            Spacer(Modifier.size(15.dp))
//            showView(controller,Modifier.fillMaxWidth())
//        }
//    else Column(Modifier.padding(20.dp)) {
//        saleView(controller, modifier = Modifier.fillMaxWidth())
//        Spacer(Modifier.size(15.dp))
//        showView(controller,Modifier.fillMaxWidth())
//    }
    adaptiveLayout(
        { m ->
            saleView(productList, m) {
                scope.launch {
                    controller.saveSale(it)
                    refreshSaleList()
                }
            }
        }, { m ->
            showView(
                saleList,
                m,
                {
                    date = it
                    scope.launch {
                        refreshSaleList()
                    }
                }
                ) {
                scope.launch {
                    controller.deleteSale(it)
                    refreshSaleList()
                }
            }
        }
    )

}
