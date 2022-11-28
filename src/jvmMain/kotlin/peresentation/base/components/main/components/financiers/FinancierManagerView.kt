package peresentation.base.components.main.components.financiers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import domain.financier.model.Financier
import peresentation.base.components.main.components.financiers.components.financierListItem
import peresentation.common.components.adaptiveLayout
import peresentation.common.components.inputTextFiled

@Composable
fun financierManagerView() {
    val controller = FinancierViewController()
    adaptiveLayout(
        {addFinancier(it)},
        {showFinanciers(it,controller.getFinanciers())}
    )
}

@Composable
fun addFinancier(modifier: Modifier = Modifier){
    var name : String by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    Column(modifier) {
        inputTextFiled(
            name,
            {name = it},
            label = "نام",
            modifier = Modifier.fillMaxWidth()
        )
        inputTextFiled(
            description,
            {description = it},
            label = "توضیحات (اختیاری)",
            modifier = Modifier.fillMaxWidth(),
            lines = 4
        )
        Button({},Modifier.align(Alignment.End)){
            Text("ثبت")
        }
    }
}

@Composable
fun showFinanciers(modifier: Modifier = Modifier,list: List<Financier>){
    LazyColumn(modifier.padding(10.dp)) {
        items(list){
            financierListItem(name = it.name, description = it.description)
            Spacer(Modifier.size(5.dp))
        }
    }
}

