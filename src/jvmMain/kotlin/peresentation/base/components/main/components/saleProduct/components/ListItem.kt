package peresentation.base.components.main.components.saleProduct.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.utils.TextUtils
import domain.sale.model.Sale
import peresentation.common.components.listItemText
import java.util.Date

@Composable
fun listItem(title: String,quantity: String, totalPrice: String, date: String){
    Row(
        Modifier
            .border(1.dp, MaterialTheme.colors.onSecondary, RoundedCornerShape(5.dp))
    ) {
        listItemText(
            title,
            Modifier
                .weight(2f)
        )
        listItemText(
            quantity,
            Modifier
                .weight(1f)
        )
        listItemText(
            TextUtils.addSeparator(totalPrice),
            Modifier
                .weight(1f)
        )
        listItemText(
            date,
            Modifier
                .weight(1f)
        )
    }
}