package peresentation.base.components.main.components.addProduct.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import peresentation.common.components.listItemText

@Composable
fun showListHeader(){
    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            Modifier
                .background(MaterialTheme.colors.secondary,shape = RoundedCornerShape(10.dp))
                .padding(5.dp)
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
            listItemText(
                "تاریخ خرید",
                Modifier
                    .weight(1f)
            )
            Spacer(Modifier.weight(1f))
        }
    }
}