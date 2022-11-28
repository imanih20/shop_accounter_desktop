package peresentation.base.components.main.components.statistics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import common.utils.TextUtils

@Composable
fun statisticView(
    title: String,
    dayStatistic: Int,
    monthStatistic: Int,
    yearStatistic: Int,
    modifier: Modifier = Modifier
){
    Column(modifier) {
        Text("آمار $title",Modifier.align(Alignment.Start))
        Spacer(Modifier.size(15.dp))
        Row {
            statisticCardView(
                Modifier.weight(1f),
                backgroundColor = MaterialTheme.colors.primary,
                title = "آمار سال",
                statistic = yearStatistic
            )
            Spacer(Modifier.size(15.dp))
            statisticCardView(
                Modifier.weight(1f),
                backgroundColor = MaterialTheme.colors.primary,
                title = "آمار ماه",
                statistic = monthStatistic
            )
            Spacer(Modifier.size(15.dp))
            statisticCardView(
                Modifier.weight(1f),
                backgroundColor = MaterialTheme.colors.primary,
                title = "آمار روز",
                statistic = dayStatistic
            )
        }
    }
}
@Composable
fun statisticCardView(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    title: String,
    statistic: Int
) {
    Card(
        modifier,
        shape = RoundedCornerShape(30.dp),
        elevation = 10.dp,
        backgroundColor = backgroundColor,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 35.dp).background(backgroundColor),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(title, fontSize = 15.sp)
            Spacer(Modifier.size(5.dp))
            Text(TextUtils.addSeparator(statistic.toString()), fontSize = 24.sp)
        }
    }
}



