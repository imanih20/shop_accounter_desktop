package peresentation.base.components.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import common.consts.GRAY

@Composable
fun headerView(modifier: Modifier = Modifier,title: String){
    Row(modifier.background(GRAY).padding(10.dp),horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {

        Text(title, fontSize = 20.sp)
    }
}