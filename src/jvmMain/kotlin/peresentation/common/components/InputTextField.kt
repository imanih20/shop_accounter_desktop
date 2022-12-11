package peresentation.common.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import common.utils.TextUtils

@Composable
fun inputTextFiled(
    value: String,
    onValueChange: (String)->Unit,
    modifier: Modifier = Modifier,
    label: String,
    readOnly: Boolean = false,
    lines: Int = 1,
    isError : Boolean = false
){
    OutlinedTextField(
        value,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.body1,
        label = {
            Text(label)
        },
        maxLines = lines,
        singleLine = lines == 1,
        modifier = modifier,
        readOnly = readOnly,
        isError = isError
    )
}