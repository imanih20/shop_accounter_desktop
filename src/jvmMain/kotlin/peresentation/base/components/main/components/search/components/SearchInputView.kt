package peresentation.base.components.main.components.search.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.financier.model.Financier
import peresentation.common.components.dropDownTextFiled
import peresentation.common.components.inputTextFiled


@Composable
fun searchInputView(modifier: Modifier = Modifier, financiers: List<Financier>, onQueryChange: (query:String, owner:String)->Unit) {
    var owner by remember { mutableStateOf("") }
    var query by remember { mutableStateOf("") }
    Column (modifier){
        Row {
            inputTextFiled(
                query,
                {
                    query = it
                    onQueryChange(it,owner)
                },
                label = "اسم کالای مورد نظر  را وارد کنید"
            )
            Spacer(Modifier.size(5.dp))
            dropDownTextFiled(
                Modifier.weight(1f),
                financiers,
                label = "صاحب کالا",
                onItemSelected = {
                    owner = it?.toString() ?: ""
                    onQueryChange(query,owner)
                },
                enabled = financiers.size > 1,
            )
        }
    }
}
