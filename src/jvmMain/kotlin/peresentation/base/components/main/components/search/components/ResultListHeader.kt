package peresentation.base.components.main.components.search.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common.consts.CARD_ELEVATION
import common.consts.CORNER_RADIUS
import peresentation.common.components.listItemText

@Composable
fun resultListHeader(modifier: Modifier = Modifier) {
    Card(
        modifier,
        elevation = CARD_ELEVATION,
        shape = RoundedCornerShape(CORNER_RADIUS),
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            listItemText("اسم", Modifier.weight(1f).align(Alignment.CenterVertically))
            listItemText("تعداد", Modifier.weight(1f).align(Alignment.CenterVertically))
            listItemText("قیمت خرید", Modifier.weight(1f).align(Alignment.CenterVertically))
            listItemText("قیمت فروش", Modifier.weight(1f).align(Alignment.CenterVertically))
            listItemText("سهم بر", Modifier.weight(1f).align(Alignment.CenterVertically))
            Spacer(Modifier.weight(1f))
        }
    }
}