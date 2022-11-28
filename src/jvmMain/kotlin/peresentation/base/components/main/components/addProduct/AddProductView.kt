package peresentation.base.components.main.components.addProduct

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common.utils.Controller
import peresentation.base.components.main.components.addProduct.components.addView
import peresentation.base.components.main.components.addProduct.components.showProductView
import peresentation.common.components.adaptiveLayout

@Composable
fun addProductView(){
    val controller = AddProductController()
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
            addView(controller,m)
        },{m ->
            showProductView(controller,m)
        }
    )
}






