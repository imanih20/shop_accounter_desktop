package peresentation.base.components.main.components.saleProduct

import androidx.compose.runtime.Composable
import peresentation.base.components.main.components.saleProduct.components.saleView
import peresentation.base.components.main.components.saleProduct.components.showView
import peresentation.common.components.adaptiveLayout

@Composable
fun saleProductView() {
    val controller = SaleProductController()
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
            saleView(controller,m)
        },{m ->
            showView(controller,m)
        }
    )

}
