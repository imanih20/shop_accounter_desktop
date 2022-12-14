package peresentation.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.consts.GRAY
import peresentation.base.components.header.headerView
import peresentation.base.components.main.components.addProduct.addProductView
import peresentation.base.components.main.components.financiers.financierManagerView
import peresentation.base.components.main.components.saleProduct.saleProductView
import peresentation.base.components.main.components.search.searchView
import peresentation.base.components.main.components.statistics.statisticsView
import peresentation.base.components.main.mainView
import peresentation.base.components.menu.MenuItems
import peresentation.base.components.menu.menuView

@Composable
fun baseCompose() {
    var menuItemSelected by remember { mutableStateOf(MenuItems.PURCHASE) }
    fun getTitle(menuItem: MenuItems) : String{
        return when(menuItem){
            MenuItems.PURCHASE-> "خرید"
            MenuItems.SALE-> "فروش"
            MenuItems.FINANCIER-> "سهام داران"
            MenuItems.STATISTICS-> "آمار"
            MenuItems.SEARCH-> "جستجو"
        }
    }
    Column(Modifier.background(GRAY)) {
        headerView(Modifier.fillMaxWidth(),title = getTitle(menuItemSelected))
        Row(Modifier.fillMaxSize()) {
            menuView(Modifier.wrapContentSize()){
                menuItemSelected = it
            }
            mainView(menuItemSelected)
        }
    }
}