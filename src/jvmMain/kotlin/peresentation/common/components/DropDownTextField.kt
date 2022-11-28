package peresentation.common.components

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.toSize

@Composable
fun <T> dropDownTextFiled(
    modifier: Modifier = Modifier,
    suggestions: List<T>,
    label: String,
    onItemSelected: (T?)->Unit,
    enabled: Boolean = true,
    readOnly: Boolean = false,
) {
    val defaultValue : T? = suggestions.firstOrNull()
    var expanded by remember { mutableStateOf(false) }
    var selected : T? by remember { mutableStateOf(defaultValue) }
    var textFiledValue by remember { mutableStateOf(defaultValue?.toString() ?: "") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    Column(
        modifier.wrapContentSize(Alignment.TopStart),
        horizontalAlignment = Alignment.Start,
    ) {
        OutlinedTextField(
            value = textFiledValue,
            onValueChange = {
                textFiledValue = it
            },
            modifier = Modifier
                .align(Alignment.Start)
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textFieldSize = coordinates.size.toSize()
                }
                .onFocusChanged { expanded = it.hasFocus }
            ,
            label = { Text(label) },
            readOnly = readOnly,
            textStyle = MaterialTheme.typography.body1,

            )
        if(enabled)
            DropdownMenu(
                expanded = expanded,
                focusable = false,
                onDismissRequest = {
                    expanded = false
                    onItemSelected(selected)
                },
                modifier = Modifier
                    .align(Alignment.Start)
                    .width(with(LocalDensity.current){textFieldSize.width.toDp()}),
            ) {
                suggestions
                    .filter { it.toString().contains(textFiledValue) }
                    .forEach { t ->
                        DropdownMenuItem(onClick = {
                            selected = t
                            textFiledValue = t.toString()
                            onItemSelected(selected)
                            expanded = false
                        }) {
                            Text(text = t.toString())
                        }
                    }
            }
    }
}