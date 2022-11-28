package peresentation.base.components.main.components.addProduct.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import common.utils.TextUtils
import domain.product.model.Product
import peresentation.common.components.listItemText

@Composable
fun showListItem(product: Product){
    Card(
        elevation = 2.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            Modifier
                .border(1.dp, MaterialTheme.colors.onSecondary, RoundedCornerShape(5.dp))
        ) {
            listItemText(
                product.title,
                Modifier
                    .weight(2f)
            )
            listItemText(
                TextUtils.onlyNumberString(product.quantity.toString()),
                Modifier
                    .weight(1f)
            )
            listItemText(
                TextUtils.addSeparator(product.purchasePrice.toString()),
                Modifier
                    .weight(1f)
            )
            listItemText(
                TextUtils.addSeparator(product.salePrice.toString()),
                Modifier
                    .weight(1f)
            )
            listItemText(
                product.owner,
                Modifier
                    .weight(1f)
            )
        }
    }
}
