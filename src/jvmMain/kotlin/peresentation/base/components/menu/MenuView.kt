package peresentation.base.components.menu

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import common.consts.CORNER_RADIUS
import common.consts.GRAY
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.*

enum class MenuItems {
    PURCHASE,
    SALE,
    FINANCIER,
    STATISTICS,
    SEARCH
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun menuView(modifier: Modifier = Modifier, onMenuItemSelected: (item: MenuItems) -> Unit) {
    var selected by remember { mutableStateOf(MenuItems.FINANCIER) }
    var activate by remember { mutableStateOf(false) }
    onMenuItemSelected(selected)
    BoxWithConstraints(modifier.wrapContentSize()) {

        Column(
            Modifier
                .fillMaxHeight()
                .background(GRAY)
                .padding(vertical = 15.dp, horizontal = 10.dp)
                .onPointerEvent(PointerEventType.Enter) {
                    activate = true
                }
                .onPointerEvent(PointerEventType.Exit) { activate = false },
            horizontalAlignment = Alignment.Start,
        ) {
            val itemModifier = Modifier
//        Icon(Icons.Filled.Menu,"",Modifier.size(35.dp).align(Alignment.CenterHorizontally))
//        Spacer(Modifier.size(25.dp))
            menuItem(
                itemModifier,
                FontAwesomeIcons.Solid.User,
                "سهامداران",
                selected == MenuItems.FINANCIER,
                activate
            ) {
                selected = MenuItems.FINANCIER
            }
            Spacer(Modifier.size(10.dp))
            menuItem(
                itemModifier,
                FontAwesomeIcons.Solid.ShoppingCart,
                "خرید",
                selected == MenuItems.PURCHASE,
                activate
            ) {
                selected = MenuItems.PURCHASE
            }
            Spacer(Modifier.size(10.dp))
            menuItem(itemModifier, FontAwesomeIcons.Solid.ShoppingBag, "فروش", selected == MenuItems.SALE, activate) {
                selected = MenuItems.SALE
            }
            Spacer(Modifier.size(10.dp))
            menuItem(
                itemModifier,
                FontAwesomeIcons.Solid.ChartLine,
                "آمار",
                selected == MenuItems.STATISTICS,
                activate
            ) {
                selected = MenuItems.STATISTICS
            }
            Spacer(Modifier.size(10.dp))
            menuItem(itemModifier, FontAwesomeIcons.Solid.Search, "جستجو", selected == MenuItems.SEARCH, activate) {
                selected = MenuItems.SEARCH
            }
        }

    }
}

@Composable
fun menuItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    selected: Boolean = false,
    activate: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .background(
                    if (selected)
                        MaterialTheme.colors.primary
                    else
                        MaterialTheme.colors.background,
                    shape = RoundedCornerShape(CORNER_RADIUS)
                )
                .padding(6.dp)
        ) {
            Icon(
                icon,
                "",
                tint = if (selected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onBackground,
                modifier = Modifier.size(23.dp)
            )
        }
//        if (activate)
        AnimatedVisibility(
            visible = activate,
            enter = expandHorizontally(),
            exit = shrinkHorizontally(),
        ) {
            Spacer(Modifier.size(15.dp))
        }
        AnimatedVisibility(
            visible = activate,
            enter = expandHorizontally(),
            exit = shrinkHorizontally(),
        ) {
            Text(
                title,
                color = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground,
                fontSize = 18.sp
            )
        }
    }
}
