package peresentation.base.components.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import peresentation.base.components.main.components.addProduct.addProductView
import peresentation.base.components.main.components.financiers.financierManagerView
import peresentation.base.components.main.components.saleProduct.saleProductView
import peresentation.base.components.main.components.search.searchView
import peresentation.base.components.main.components.statistics.statisticsView
import peresentation.base.components.menu.MenuItems

@Composable
fun mainView(selectedItem: MenuItems,modifier: Modifier = Modifier){
    Card(
        modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        shape = RoundedCornerShape(20.dp)
    ) {
        when(selectedItem){
            MenuItems.PURCHASE-> addProductView()
            MenuItems.SALE-> saleProductView()
            MenuItems.FINANCIER-> financierManagerView()
            MenuItems.STATISTICS-> statisticsView()
            MenuItems.SEARCH-> searchView()
            else -> {}
        }
    }
}