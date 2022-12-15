package peresentation.base.components.main.components.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.dp
import common.consts.CARD_ELEVATION
import common.consts.CORNER_RADIUS
import common.utils.TextUtils
import domain.product.model.Product
import peresentation.common.components.listItemText

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun resultItemView(
    modifier: Modifier = Modifier,
    product: Product,
    onEditClicked: (Product) -> Unit,
    onDeleteClicked: (Int) -> Unit
) {
    var activate by remember { mutableStateOf(false) }
    Card(
        modifier
            .onPointerEvent(PointerEventType.Enter) { activate = true }
            .onPointerEvent(PointerEventType.Exit) { activate = false },
        elevation = CARD_ELEVATION,
        shape = RoundedCornerShape(CORNER_RADIUS),
        backgroundColor = if (product.quantity <= 0) Color.Gray else MaterialTheme.colors.background
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)) {
            listItemText(product.title, Modifier.weight(1f).align(Alignment.CenterVertically))
            listItemText(
                TextUtils.showNumberString(product.quantity.toString()),
                Modifier.weight(1f).align(Alignment.CenterVertically)
            )
            listItemText(
                TextUtils.addSeparator(product.purchasePrice.toString()),
                Modifier.weight(1f).align(Alignment.CenterVertically)
            )
            listItemText(
                TextUtils.addSeparator(product.salePrice.toString()),
                Modifier.weight(1f).align(Alignment.CenterVertically)
            )
            listItemText(product.owner, Modifier.weight(1f).align(Alignment.CenterVertically))
            Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                if (activate) {
                    Icon(Icons.Filled.Edit, "ویرایش", tint = Color.Green, modifier = Modifier.size(20.dp).clickable {
                        onEditClicked(product)
                    })
                    Spacer(Modifier.size(10.dp))
                    Icon(Icons.Filled.Delete, "حذف", tint = Color.Red, modifier = Modifier.size(20.dp).clickable {
                        onDeleteClicked(product.id)
                    })
                }
            }
        }
    }
}