package peresentation.base.components.main.components.addProduct.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import common.consts.CARD_ELEVATION
import common.consts.CORNER_RADIUS
import common.consts.LIST_ITEM_PADDING
import common.utils.TextUtils
import domain.trade.model.ProductTrade
import peresentation.common.components.listItemText

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun showListItem(trade: ProductTrade, onDeleteIconClicked: (Int) -> Unit) {
    var activate by remember { mutableStateOf(false) }
    Card(
        elevation = CARD_ELEVATION,
        shape = RoundedCornerShape(CORNER_RADIUS),
        modifier = Modifier
            .onPointerEvent(PointerEventType.Enter) { activate = true }
            .onPointerEvent(PointerEventType.Exit) { activate = false },
    ) {
        Row(
            Modifier
                .padding(LIST_ITEM_PADDING)
        ) {
            listItemText(
                trade.title,
                Modifier
                    .weight(2f)
            )
            listItemText(
                TextUtils.showNumberString(trade.quantity.toString()),
                Modifier
                    .weight(1f)
            )
            listItemText(
                TextUtils.addSeparator(trade.totalPrice.toString()),
                Modifier
                    .weight(1f)
            )
            Row(Modifier.weight(1f), horizontalArrangement = Arrangement.Center) {
                if (activate) Icon(
                    Icons.Filled.Delete,
                    "حذف",
                    tint = Color.Red,
                    modifier = Modifier.clickable {
                        onDeleteIconClicked(trade.id)
                    })
            }
        }
    }
}
