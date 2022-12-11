package peresentation.base.components.main.components.financiers.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.financier.model.Financier
import peresentation.common.components.deleteDialog

@Composable
fun showFinanciers(modifier: Modifier = Modifier, list: List<Financier>,onEditRequest:(Financier)->Unit,onDeleteRequest:(Int)->Unit){
    var editVisible by remember { mutableStateOf(false) }
    var deleteVisible by remember { mutableStateOf(false) }
    var financier by remember { mutableStateOf<Financier?>(null) }
    var id by remember { mutableStateOf(0) }
    Box(modifier){
        if (deleteVisible) deleteDialog({deleteVisible = false}) {
            onDeleteRequest(id)
            deleteVisible = false
        }
        if (editVisible) editFinancierDialog({editVisible=false},
            financier!!,
            onPositiveButtonClicked = {
                if (it!=null){
                    onEditRequest(it)
                }
                editVisible = false
            }
        )
        LazyColumn(Modifier.padding(10.dp)) {
            items(list){
                financierListItem(financier = it,
                    onEditClicked = {f->
                        financier = f
                        editVisible = true
                    },
                    onDeleteClicked = {i->
                        id = i
                        deleteVisible = true
                    }
                )
                Spacer(Modifier.size(5.dp))
            }
        }
    }
}
