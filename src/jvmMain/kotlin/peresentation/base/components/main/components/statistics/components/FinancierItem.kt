package peresentation.base.components.main.components.statistics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import common.consts.CORNER_RADIUS
import domain.financier.model.Financier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun financierItem(
    financier: Financier,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val textColor = if (isSelected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onBackground
    ListItem(
        modifier = Modifier
            .background(
                if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.background,
                shape = RoundedCornerShape(CORNER_RADIUS)
            )
            .clickable {
                onClick()
            },
        icon = {},
        text = {
            Text(financier.name, color = textColor, fontSize = 20.sp)
        },
        secondaryText = {
            Text("درصد سود ${financier.share}", color = textColor)
        },
    )
}