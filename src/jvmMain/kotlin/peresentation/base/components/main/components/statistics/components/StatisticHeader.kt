package peresentation.base.components.main.components.statistics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common.consts.CARD_ELEVATION
import common.consts.CORNER_RADIUS
import common.consts.LIST_ITEM_PADDING
import peresentation.common.components.listItemText

@Composable
fun statisticHeader() {
    Card(
        elevation = CARD_ELEVATION,
        shape = RoundedCornerShape(CORNER_RADIUS)
    ) {
        Row(
            Modifier
                .background(MaterialTheme.colors.secondary, shape = RoundedCornerShape(CORNER_RADIUS))
                .padding(LIST_ITEM_PADDING), verticalAlignment = Alignment.CenterVertically
        ) {
            listItemText("ماه", Modifier.weight(1f))
            listItemText("کل خرید", Modifier.weight(1f))
            listItemText("کل فروش", Modifier.weight(1f))
            listItemText("کل سود", Modifier.weight(1f))
            listItemText("وضعیت پرداخت", Modifier.weight(1f))
        }
    }
}