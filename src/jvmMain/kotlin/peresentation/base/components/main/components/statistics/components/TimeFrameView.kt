package peresentation.base.components.main.components.statistics.components

import LocalWindowSize
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.utils.WindowSize
import peresentation.common.components.inputTextFiled
import peresentation.base.components.main.components.statistics.StatisticsController

@Composable
fun timeFrameView(controller: StatisticsController, modifier: Modifier) {
    var year by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }
    var day by remember { mutableStateOf("") }

    if (LocalWindowSize.current == WindowSize.EXPAND)
        Column(modifier, verticalArrangement = Arrangement.Top) {
            inputTextFiled(
                year,
                { year = it },
                Modifier.fillMaxWidth(),
                label = "سال"
            )
            Spacer(Modifier.size(5.dp))
            inputTextFiled(
                month,
                { month = it },
                Modifier.fillMaxWidth(),
                label = "ماه"
            )
            Spacer(Modifier.size(5.dp))
            inputTextFiled(
                day,
                { day = it },
                Modifier.fillMaxWidth(),
                label = "روز"
            )
            Spacer(Modifier.size(5.dp))
            Button({}, Modifier.align(Alignment.End)) {
                Text("مشاهده")
            }

        }
    else {
        Column {
            Row(modifier.wrapContentSize()) {
                inputTextFiled(
                    year,
                    {year = it},
                    Modifier.weight(1f),
                    label = "سال"
                )
                Spacer(Modifier.size(5.dp))
                inputTextFiled(
                    month,
                    {month = it},
                    Modifier.weight(1f),
                    label = "ماه"
                )
                Spacer(Modifier.size(5.dp))
                inputTextFiled(
                    day,
                    {day = it},
                    Modifier.weight(1f),
                    label = "روز"
                )
            }
            Spacer(Modifier.size(5.dp))
            Button({

            }){
                Text("مشاهده")
            }

        }
    }
}
