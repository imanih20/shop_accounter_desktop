package peresentation.base.components.main.components.saleProduct.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.consts.CARD_ELEVATION
import common.consts.CORNER_RADIUS
import common.consts.LIST_ITEM_PADDING
import peresentation.common.components.datePicker
import peresentation.common.components.listItemText

@Composable
fun saleListHeader(onDateChanged: (String)->Unit) {
    Card(
        elevation = CARD_ELEVATION,
        shape = RoundedCornerShape(CORNER_RADIUS)
    ) {
        Row(
            Modifier
                .background(MaterialTheme.colors.secondary, shape = RoundedCornerShape(10.dp))
                .padding(LIST_ITEM_PADDING)
        ) {
            listItemText(
                "اسم کالا",
                Modifier
                    .weight(2f)
            )
            listItemText(
                "تعداد",
                Modifier
                    .weight(1f)
            )
            listItemText(
                "قیمت کل",
                Modifier
                    .weight(1f)
            )
            datePicker(modifier = Modifier.weight(1f)){
                onDateChanged(it)
            }
        }
    }
}

