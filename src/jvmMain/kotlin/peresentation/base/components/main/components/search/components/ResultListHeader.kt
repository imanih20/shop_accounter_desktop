package peresentation.base.components.main.components.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.dp
import common.utils.TextUtils
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Tag
import peresentation.common.components.listItemText

@Composable
fun resultListHeader(modifier : Modifier = Modifier){
    Card(
        modifier,
        elevation = 3.dp,
        shape = RoundedCornerShape(3.dp),
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            listItemText("اسم", Modifier.weight(1f).align(Alignment.CenterVertically))
            listItemText("تعداد", Modifier.weight(1f).align(Alignment.CenterVertically))
            listItemText("قیمت خرید", Modifier.weight(1f).align(Alignment.CenterVertically))
            listItemText("قیمت فروش", Modifier.weight(1f).align(Alignment.CenterVertically))
            listItemText("فروشنده", Modifier.weight(1f).align(Alignment.CenterVertically))
            listItemText("تاریخ خرید", Modifier.weight(1f).align(Alignment.CenterVertically))

            Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {

            }
        }
    }
}