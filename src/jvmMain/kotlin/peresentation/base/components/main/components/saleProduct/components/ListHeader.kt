package peresentation.base.components.main.components.saleProduct.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import peresentation.common.components.listItemText

@Composable
fun saleListHeader(){
    Row(
        Modifier
            .background(MaterialTheme.colors.secondary,shape = RoundedCornerShape(5.dp))
            .border(1.dp, MaterialTheme.colors.onSecondary, RoundedCornerShape(5.dp))
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
            "تاریخ",
            Modifier
                .weight(1f)
        )
    }
}

