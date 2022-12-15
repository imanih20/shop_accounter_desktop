package peresentation.base.components.main.components.statistics.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import common.consts.CARD_ELEVATION
import common.consts.CORNER_RADIUS
import common.consts.LIST_ITEM_PADDING
import common.utils.TextUtils
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.CheckCircle
import compose.icons.fontawesomeicons.solid.TimesCircle
import domain.statistic.model.Statistic
import peresentation.common.components.listItemText

@Composable
fun statisticItem(statistic: Statistic, onCheckedChange: (Boolean) -> Unit) {
    Card(
        elevation = CARD_ELEVATION,
        shape = RoundedCornerShape(CORNER_RADIUS)
    ) {
        Row(
            Modifier
                .padding(LIST_ITEM_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            listItemText("${statistic.year}-${statistic.month}", Modifier.weight(1f))
            listItemText(TextUtils.addSeparator(statistic.totalPurchase.toString()), Modifier.weight(1f))
            listItemText(TextUtils.addSeparator(statistic.totalSale.toString()), Modifier.weight(1f))
            listItemText(TextUtils.addSeparator(statistic.totalIncome.toString()), Modifier.weight(1f))
            Row(Modifier.weight(1f), horizontalArrangement = Arrangement.Center) {
                Box(Modifier.size(25.dp)) {
                    var isChecked by remember { mutableStateOf(false) }
                    isChecked = statistic.isPaid
                    Icon(
                        if (isChecked) FontAwesomeIcons.Solid.CheckCircle
                        else FontAwesomeIcons.Solid.TimesCircle,
                        "",
                        tint = if (isChecked) Color.Green else Color.Red,
                        modifier = Modifier.clickable {
                            isChecked = !isChecked
                            onCheckedChange(isChecked)
                        }
                    )
                }
            }
        }
    }
}