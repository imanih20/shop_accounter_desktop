package peresentation.base.components.main.components.statistics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import common.utils.TextUtils
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.CheckCircle
import compose.icons.fontawesomeicons.solid.TimesCircle
import peresentation.common.components.listItemText

@Composable
fun statisticHeader(){
    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            Modifier
                .background(MaterialTheme.colors.secondary,shape = RoundedCornerShape(10.dp))
                .padding(5.dp)
            , verticalAlignment = Alignment.CenterVertically
        ) {
            listItemText("ماه", Modifier.weight(1f))
            listItemText("کل خرید", Modifier.weight(1f))
            listItemText("کل فروش", Modifier.weight(1f))
            listItemText("کل سود", Modifier.weight(1f))
            listItemText("وضعیت پرداخت", Modifier.weight(1f))
        }
    }
}