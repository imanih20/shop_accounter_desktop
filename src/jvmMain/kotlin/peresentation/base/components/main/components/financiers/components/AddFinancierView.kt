package peresentation.base.components.main.components.financiers.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.utils.TextUtils
import domain.financier.model.Financier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import peresentation.common.components.inputTextFiled
import peresentation.common.components.toast

@Composable
fun addFinancier(modifier: Modifier = Modifier, saveFinancier: (Financier)->Flow<Boolean>){
    var name : String by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var share : String by remember { mutableStateOf("") }
    //ui actions
    var isNameError by remember { mutableStateOf(false) }
    var isShareError by remember { mutableStateOf(false) }
    var notice by remember { mutableStateOf("ذخیره شده") }
    var toastError by remember { mutableStateOf(false) }
    val hostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    fun clearInputs(){
        name = ""
        description = ""
        share = ""
    }
    Column(modifier, verticalArrangement = Arrangement.SpaceBetween) {
        Column {
            Row(Modifier.fillMaxWidth()) {
                inputTextFiled(
                    name,
                    {name = it},
                    label = "نام",
                    modifier = Modifier.weight(2f),
                    isError = isNameError
                )
                Spacer(Modifier.size(5.dp))
                inputTextFiled(
                    share,
                    {share = TextUtils.showNumberString(it)},
                    label = "درصد سود",
                    modifier = Modifier.weight(1f),
                    isError = isShareError
                )
            }
            Spacer(Modifier.size(5.dp))
            inputTextFiled(
                description,
                {description = it},
                label = "توضیحات (اختیاری)",
                modifier = Modifier.fillMaxWidth(),
                lines = 4
            )
            Button({
                isNameError = name.isEmpty()
                isShareError = share.isEmpty()
                if (!isNameError&&!isShareError) {
                    val financier = Financier(name = name, description = description, share = share.toInt())
                    scope.launch {
                        saveFinancier(financier).collect {
                            if (it) {
                                toastError = false
                                notice = "ثبت شد"
                                clearInputs()
                            } else {
                                toastError = true
                                notice = "این نام در حال حاظر موجود است."
                            }
                        }
                    }
                } else {
                    toastError = true
                    notice = "فیلد های قرمز را پر کنید"
                }
                scope.launch {
                    hostState.showSnackbar(notice)
                }
            }, Modifier.align(Alignment.End)){
                Text("ثبت")
            }
        }
        toast(hostState,toastError)
    }
}
