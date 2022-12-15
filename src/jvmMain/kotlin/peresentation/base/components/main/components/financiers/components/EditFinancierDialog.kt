package peresentation.base.components.main.components.financiers.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import common.consts.SPACER_SIZE
import domain.financier.model.Financier
import peresentation.common.components.inputTextFiled

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun editFinancierDialog(
    onCloseRequest: () -> Unit,
    financier: Financier,
    onPositiveButtonClicked: (Financier?) -> Unit,
) {
    var name by remember { mutableStateOf(financier.name) }
    var isEdited by remember { mutableStateOf(false) }
    var share by remember { mutableStateOf(financier.share.toString()) }
    var description by remember { mutableStateOf(financier.description) }
    Box(Modifier.wrapContentSize()) {
        AlertDialog(
            { onCloseRequest() },
            text = {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    Column {
                        Row {
                            inputTextFiled(
                                name,
                                {
                                    name = it
                                    isEdited = true
                                },
                                modifier = Modifier.weight(1f),
                                label = "نام"
                            )
                            Spacer(Modifier.size(SPACER_SIZE))
                            inputTextFiled(
                                share,
                                {
                                    share = it
                                    isEdited = true
                                },
                                modifier = Modifier.weight(1f),
                                label = "درصد سود"
                            )
                        }
                        Spacer(Modifier.size(SPACER_SIZE))
                        inputTextFiled(
                            description,
                            {
                                description = it
                                isEdited = true
                            },
                            label = "توضیحات"
                        )
                    }
                }
            },
            confirmButton = {
                TextButton({
                    if (isEdited) {
                        onPositiveButtonClicked(Financier(financier.id, name, description, share.toInt()))
                    } else {
                        onPositiveButtonClicked(null)
                    }
                }) {
                    Text("ثبت")
                }
            }, dismissButton = {
                TextButton({
                    onCloseRequest()
                }) {
                    Text("لغو")
                }
            }
        )
    }
}