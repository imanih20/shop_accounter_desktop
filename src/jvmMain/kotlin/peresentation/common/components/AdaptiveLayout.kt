package peresentation.common.components

import LocalWindowSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import common.utils.WindowSize

@Composable
fun adaptiveLayout(content1: @Composable (Modifier) -> Unit, content2: @Composable (Modifier) -> Unit) {
    val color = 0xaaffff
    if (LocalWindowSize.current == WindowSize.EXPAND)
        Row(Modifier.padding(20.dp), verticalAlignment = Alignment.Top) {
            content1(Modifier.fillMaxHeight().fillMaxWidth(0.3f).background(Color(color)).align(Alignment.Top))
            Spacer(Modifier.size(15.dp))
            Box(Modifier.fillMaxHeight().width(1.dp).background(Color.Black))
            Spacer(Modifier.size(15.dp))
            content2(Modifier.fillMaxHeight().fillMaxWidth().background(Color(color)))
        }
    else
        Column(Modifier.padding(20.dp)) {
            content1(Modifier.fillMaxWidth().background(Color(color)))
            Spacer(Modifier.size(15.dp))
            content2(Modifier.fillMaxWidth().background(Color(color)))
        }
}