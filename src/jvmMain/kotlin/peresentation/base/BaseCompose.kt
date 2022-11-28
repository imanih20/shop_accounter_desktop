package peresentation.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import peresentation.base.components.main.mainView
import peresentation.base.components.menu.MenuItems
import peresentation.base.components.menu.menuView

@Composable
fun baseCompose() {
    var menuItemSelected by remember { mutableStateOf(MenuItems.PURCHASE) }
    Row(Modifier.fillMaxSize().background(MaterialTheme.colors.secondary)) {
        menuView{
            menuItemSelected = it
        }
        mainView(menuItemSelected)
    }
}