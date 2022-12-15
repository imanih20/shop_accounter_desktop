package peresentation.common.components

import androidx.compose.animation.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.onFocusedBoundsChanged
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.AngleLeft
import compose.icons.fontawesomeicons.solid.AngleRight
import compose.icons.fontawesomeicons.solid.CaretDown
import compose.icons.fontawesomeicons.solid.CaretUp
import ir.huri.jcal.JalaliCalendar
import kotlinx.coroutines.launch

@Composable
fun inputDatePicker(
    initialDate: JalaliCalendar = JalaliCalendar(),
    yearRange: IntRange = 1380..1410,
    onDateChanged: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var date by remember { mutableStateOf(initialDate.toString()) }
    inputTextFiled(
        date,
        {
            date = it
            onDateChanged(it)
        },
        label = "",
        modifier = Modifier.onFocusChanged { expanded = it.hasFocus }
    )
    DropdownMenu(
        expanded = expanded,
        focusable = false,
        onDismissRequest = {
            expanded = false
        },
    ) {
        DropdownMenuItem(onClick = {}, enabled = false) {
            Column {
                persianCalender(date, yearRange) {
                    date = it
                    onDateChanged(it)
                }
                TextButton({ expanded = false }, Modifier.align(Alignment.End)) {
                    Text("خروج")
                }
            }
        }

    }
}

@Composable
fun datePicker(
    initialDate: JalaliCalendar = JalaliCalendar(),
    modifier: Modifier = Modifier,
    yearRange: IntRange = 1390..1410,
    onDateChanged: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var date by remember { mutableStateOf(initialDate.toString()) }

    Row(modifier.clickable {
        expanded = !expanded
    }, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        listItemText(date,Modifier)
        Box(Modifier.size(15.dp)){
            Icon(if (expanded) FontAwesomeIcons.Solid.CaretDown else FontAwesomeIcons.Solid.CaretUp,"")
        }
    }
    DropdownMenu(
        expanded = expanded,
        focusable = false,
        onDismissRequest = {
            expanded = false
        },
    ) {
        DropdownMenuItem(onClick = {}, enabled = false) {
            Column {
                persianCalender(date, yearRange) {
                    date = it
                    expanded = false
                    onDateChanged(it)
                }
                TextButton({ expanded = false }, Modifier.align(Alignment.End)) {
                    Text("خروج")
                }
            }
        }

    }
}

@Composable
@Preview
fun persianCalender(initialDate: String, yearRange: IntRange, onSelected: (String) -> Unit) {
    val dateFields = initialDate.split('-')
    val state = remember { DatePickerState(JalaliCalendar(dateFields[0].toInt(),dateFields[1].toInt(),dateFields[2].toInt()), yearRange) }
    var header by remember { mutableStateOf("${state.selectedDate.monthString} ${state.selectedDate.year}") }
    fun calcDayRange(month: Int): IntRange {
        return if (month < 7) 1..30 else if (month in 7..11) 1..31 else 1..29
    }

    var daysRange by remember { mutableStateOf(calcDayRange(state.selectedDate.month)) }
    Column(Modifier.height(288.dp).width(256.dp)) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            calenderHeader(
                header,
                state
            ) {
                header = "${state.selectedDate.monthString} ${state.selectedDate.year}"
                daysRange = calcDayRange(state.selectedDate.month)
                onSelected(state.selectedDate.toString())
            }
            AnimatedVisibility(
                visible = state.yearShowing,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                yearPicker(state) {
                    header = "${state.selectedDate.monthString} ${state.selectedDate.year}"
                    onSelected(state.selectedDate.toString())
                }
            }
            AnimatedVisibility(
                visible = !state.yearShowing,
                enter = slideInVertically(),
                exit = slideOutVertically()
            ) {
                calenderView(state, daysRange) {
                    onSelected(state.selectedDate.toString())

                }
            }
        }
    }
}

@Composable
fun calenderHeader(header: String, state: DatePickerState, onPreOrNextClick: () -> Unit) {
    val scope = rememberCoroutineScope()
    val arrowDropUp = FontAwesomeIcons.Solid.CaretUp
    val arrowDropDown = FontAwesomeIcons.Solid.CaretDown
    Row(
        Modifier
            .width(256.dp)
            .padding(start = 24.dp, end = 8.dp, top = 16.dp, bottom = 12.dp)
            .height(24.dp),
        horizontalArrangement = if (state.yearShowing) Arrangement.Start else Arrangement.SpaceBetween,
    ) {
        Row(Modifier
            .clickable {
                scope.launch {
                    state.yearShowing = !state.yearShowing
                }
            }
        ) {
            Text(header, Modifier.paddingFromBaseline(top = 32.dp), color = MaterialTheme.colors.onBackground)
            Spacer(Modifier.size(4.dp))
            Box(Modifier.size(24.dp), contentAlignment = Alignment.Center) {
                Icon(
                    if (state.yearShowing) arrowDropUp else arrowDropDown,
                    "انتخاب  سال",
                    tint = MaterialTheme.colors.onBackground
                )
            }
        }
        if (!state.yearShowing) {
            Row {
                Box(Modifier.size(24.dp), contentAlignment = Alignment.Center) {
                    Icon(FontAwesomeIcons.Solid.AngleRight, "ماه بعد", Modifier.clickable {
                        if (state.selectedDate.month < 12) state.selectedDate.month++ else state.selectedDate.month = 1
                        onPreOrNextClick()
                    }, tint = MaterialTheme.colors.onBackground)
                }
                Spacer(Modifier.size(24.dp))
                Box(Modifier.size(24.dp), contentAlignment = Alignment.Center) {
                    Icon(FontAwesomeIcons.Solid.AngleLeft, "ماه قبل", Modifier.clickable {
                        if (state.selectedDate.month > 1) state.selectedDate.month-- else state.selectedDate.month = 12
                        onPreOrNextClick()
                    }, tint = MaterialTheme.colors.onBackground)
                }
                Spacer(Modifier.size(12.dp))
            }
        }
    }
}

@Composable
fun yearPicker(state: DatePickerState, onSelected: () -> Unit) {
    val gridState = rememberLazyGridState()
    val scope = rememberCoroutineScope()
    var selected by remember { mutableStateOf(state.selectedDate.year) }
    scope.launch {
        gridState.scrollToItem(selected)
    }
    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Fixed(4),
        modifier = Modifier.padding(top = 4.dp, bottom = 8.dp, start = 12.dp, end = 20.dp).height(228.dp)
    ) {
        itemsIndexed(state.yearRange.toList()) { _, item ->
            yearPickerItem(item, selected == item) {
                selected = item
                state.selectedDate.year = item
                state.yearShowing = false
                onSelected()
            }
        }
    }
}

@Composable
fun yearPickerItem(year: Int, selected: Boolean = false, onClick: () -> Unit) {
    Box(Modifier.size(56.dp, 32.dp), contentAlignment = Alignment.Center) {
        Box(
            Modifier
                .size(52.dp, 28.dp)
                .background(
                    color = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.background,
                    shape = RoundedCornerShape(15.dp)
                )
                .clickable {
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                year.toString(), style = TextStyle(
                    if (selected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onBackground
                ), textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun calenderView(state: DatePickerState, dayRang: IntRange, onSelected: () -> Unit) {
    val gridState = rememberLazyGridState()
    var selected by remember { mutableStateOf(state.selectedDate.day) }
    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Fixed(7),
        modifier = Modifier.padding(top = 4.dp, bottom = 8.dp, start = 16.dp, end = 16.dp).height(228.dp)
    ) {
        itemsIndexed(dayRang.toList()) { _, item ->
            calenderItem(item, selected == item) {
                selected = item
                state.selectedDate.day = item
                onSelected()
            }
        }
    }
}

@Composable
fun calenderItem(day: Int, selected: Boolean, onClick: () -> Unit) {
    Box(Modifier.size(32.dp, 32.dp), contentAlignment = Alignment.Center) {
        Box(
            Modifier
                .size(28.dp, 28.dp)
                .background(
                    color = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.background,
                    shape = CircleShape
                )
                .clickable {
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                day.toString(), style = TextStyle(
                    if (selected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onBackground

                ), textAlign = TextAlign.Center
            )
        }
    }
}


data class DatePickerState(val initialDate: JalaliCalendar, val yearRange: IntRange) {
    var selectedDate by mutableStateOf(initialDate)
    var yearShowing by mutableStateOf(false)
}