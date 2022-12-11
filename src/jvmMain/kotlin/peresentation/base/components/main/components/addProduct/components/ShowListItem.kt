package peresentation.base.components.main.components.addProduct.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.dp
import common.utils.TextUtils
import domain.financier.model.Financier
import domain.product.model.Product
import domain.trade.model.ProductTrade
import peresentation.common.components.listItemText

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun showListItem(trade: ProductTrade,onDeleteIconClicked: (Int)->Unit){
    var activate by remember { mutableStateOf(false) }
    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .onPointerEvent(PointerEventType.Enter){activate = true}
            .onPointerEvent(PointerEventType.Exit){activate = false},
    ) {
        Row(
            Modifier
                .padding(10.dp)
        ) {
            listItemText(
                trade.title,
                Modifier
                    .weight(2f)
            )
            listItemText(
                TextUtils.onlyNumberString(trade.quantity.toString()),
                Modifier
                    .weight(1f)
            )
            listItemText(
                TextUtils.addSeparator(trade.totalPrice.toString()),
                Modifier
                    .weight(1f)
            )
            listItemText(
                trade.owner,
                Modifier
                    .weight(1f)
            )
            listItemText(
                trade.date,
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
