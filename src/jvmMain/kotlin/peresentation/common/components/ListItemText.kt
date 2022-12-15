package peresentation.common.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import common.consts.REGULAR_FONT_SIZE


@Composable
fun listItemText(title: String, modifier: Modifier) {
    Text(
        title,
        modifier
            .padding(10.dp),
        color = MaterialTheme.colors.onSecondary,
        fontSize = REGULAR_FONT_SIZE,
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Clip
    )
}
