package peresentation.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import common.consts.GRAY
import peresentation.base.components.header.headerView
import peresentation.base.components.main.mainView
import peresentation.base.components.menu.MenuItems
import peresentation.base.components.menu.menuView

@Composable
fun baseCompose() {
    var menuItemSelected by remember { mutableStateOf(MenuItems.FINANCIER) }
    fun getTitle(menuItem: MenuItems): String {
        return when (menuItem) {
            MenuItems.PURCHASE -> "خرید"
            MenuItems.SALE -> "فروش"
            MenuItems.FINANCIER -> "سهام داران"
            MenuItems.STATISTICS -> "آمار"
            MenuItems.SEARCH -> "جستجو"
        }
    }
    Column(Modifier.background(GRAY)) {
        headerView(Modifier.fillMaxWidth(), title = getTitle(menuItemSelected))
        Row(Modifier.fillMaxSize()) {
            menuView(Modifier.wrapContentSize()) {
                menuItemSelected = it
            }
            mainView(menuItemSelected)
        }
    }
}