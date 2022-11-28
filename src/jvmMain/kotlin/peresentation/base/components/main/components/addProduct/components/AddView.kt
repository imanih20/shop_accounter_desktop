package peresentation.base.components.main.components.addProduct.components

import LocalWindowSize
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.utils.DateUtils
import common.utils.TextUtils
import common.utils.WindowSize
import domain.financier.model.Financier
import peresentation.base.components.main.components.addProduct.AddProductController
import peresentation.common.components.dropDownTextFiled
import peresentation.common.components.inputTextFiled

@Composable
fun addView(controller: AddProductController, modifier: Modifier = Modifier) {
    var title by remember { mutableStateOf("") }
    var numberOfPack by remember { mutableStateOf("") }
    var numberOfItem by remember { mutableStateOf("") }
    var owner : Financier? by remember { mutableStateOf(null) }
    var totalPurchasePrice by remember { mutableStateOf("") }
    var purchasePricePerItem by remember{ mutableStateOf("0") }
    var salePricePerItem by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(DateUtils.getCurrentDate().toString()) }
    if (LocalWindowSize.current == WindowSize.EXPAND){
        Column(modifier) {
            Row {
                inputTextFiled(
                    title,
                    onValueChange = {
                        title = it
                    },
                    label ="نام کالا",
                    modifier = Modifier.weight(2f)
                )
                Spacer(Modifier.size(5.dp))
                inputTextFiled(
                    numberOfPack,
                    onValueChange = {
                        numberOfPack = TextUtils.onlyNumberString(it)
                        if (totalPurchasePrice.isNotEmpty() && numberOfItem.isNotEmpty())
                            purchasePricePerItem = controller.calculatePrice(numberOfPack.toInt(),numberOfItem.toInt(),totalPurchasePrice.toInt()).toString()
                    },
                    label ="تعداد بسته",
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(Modifier.size(5.dp))
            Row {
                inputTextFiled(
                    numberOfItem,
                    onValueChange = {
                        numberOfItem = TextUtils.onlyNumberString(it)
                        if (totalPurchasePrice.isNotEmpty() && numberOfPack.isNotEmpty())
                            purchasePricePerItem = controller.calculatePrice(numberOfPack.toInt(),numberOfItem.toInt(),totalPurchasePrice.toInt()).toString()
                    },
                    label ="تعداد آیتم هر بسته",
                    modifier = Modifier.weight(1f),
                )
                Spacer(Modifier.size(5.dp))
                inputTextFiled(
                    TextUtils.addSeparator(totalPurchasePrice),
                    onValueChange = {
                        totalPurchasePrice = TextUtils.onlyNumberString(it)
                        if (numberOfPack.isNotEmpty() && numberOfItem.isNotEmpty())
                            purchasePricePerItem = controller.calculatePrice(
                                numberOfPack.toInt(),
                                numberOfItem.toInt(),
                                totalPurchasePrice.toInt()
                            ).toString()
                    },
                    label ="قیمت کل خرید",
                    modifier = Modifier.weight(1f),
                )
            }
            Spacer(Modifier.size(5.dp))
            Row {
                inputTextFiled(
                    TextUtils.addSeparator(purchasePricePerItem),
                    onValueChange = {
                        purchasePricePerItem = TextUtils.onlyNumberString(it)
                    },
                    label = "قیمت خرید هر آیتم",
                    modifier = Modifier.weight(1f),
                    readOnly = true,
                )
                Spacer(Modifier.size(5.dp))
                inputTextFiled(
                    TextUtils.addSeparator(salePricePerItem),
                    onValueChange = {
                        salePricePerItem = TextUtils.onlyNumberString(it)
                    },
                    label ="قیمت فروش هر آیتم",
                    modifier = Modifier.weight(1f),
                )
            }
            Spacer(Modifier.size(5.dp))
            Row {
                dropDownTextFiled(
                    Modifier.weight(1f),
                    controller.getFinanciers(),
                    label = "صاحب کالا",
                    onItemSelected = {
                        owner = it
                    },
                    enabled = controller.getFinanciers().size > 1,
                )
                Spacer(Modifier.size(5.dp))
                inputTextFiled(
                    date,
                    onValueChange = {
                        date = it
                    },
                    label ="تاریخ",
                    modifier = Modifier.weight(1f),
                )
            }
            Spacer(Modifier.size(5.dp))
            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = {
                    controller.addProduct(
                        title,
                        if(numberOfPack.isNotEmpty() && numberOfItem.isNotEmpty()) numberOfPack.toInt()*numberOfItem.toInt() else 0,
                        if (purchasePricePerItem.isNotEmpty()) purchasePricePerItem.toDouble() else 0.0,
                        if (salePricePerItem.isNotEmpty()) salePricePerItem.toDouble() else 0.0,
                        owner?.id?:""
                    )
                }){
                Text("ثبت")
            }
        }
    } else {
        Column(modifier) {
            Row {
                inputTextFiled(
                    title,
                    onValueChange = {
                        title = it
                    },
                    label ="نام کالا",
                    modifier = Modifier.weight(2f)
                )
                Spacer(Modifier.size(5.dp))
                inputTextFiled(
                    numberOfPack,
                    onValueChange = {
                        numberOfPack = TextUtils.onlyNumberString(it)
                        if (totalPurchasePrice.isNotEmpty() && numberOfItem.isNotEmpty())
                            purchasePricePerItem = controller.calculatePrice(numberOfPack.toInt(),numberOfItem.toInt(),totalPurchasePrice.toInt()).toString()
                    },
                    label ="تعداد بسته",
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.size(5.dp))
                inputTextFiled(
                    numberOfItem,
                    onValueChange = {
                        numberOfItem = TextUtils.onlyNumberString(it)
                        if (totalPurchasePrice.isNotEmpty() && numberOfPack.isNotEmpty())
                            purchasePricePerItem = controller.calculatePrice(numberOfPack.toInt(),numberOfItem.toInt(),totalPurchasePrice.toInt()).toString()
                    },
                    label ="تعداد آیتم هر بسته",
                    modifier = Modifier.weight(1f),
                )
            }
            Spacer(Modifier.size(5.dp))
            Row {
                inputTextFiled(
                    TextUtils.addSeparator(totalPurchasePrice),
                    onValueChange = {
                        totalPurchasePrice = TextUtils.onlyNumberString(it)
                        if (numberOfPack.isNotEmpty() && numberOfItem.isNotEmpty())
                            purchasePricePerItem = controller.calculatePrice(
                                numberOfPack.toInt(),
                                numberOfItem.toInt(),
                                totalPurchasePrice.toInt()
                            ).toString()
                    },
                    label ="قیمت کل خرید",
                    modifier = Modifier.weight(1f),
                )
                Spacer(Modifier.size(5.dp))
                inputTextFiled(
                    TextUtils.addSeparator(purchasePricePerItem),
                    onValueChange = {
                        purchasePricePerItem = TextUtils.onlyNumberString(it)
                    },
                    label = "قیمت خرید هر آیتم",
                    modifier = Modifier.weight(1f),
                    readOnly = true,
                )
                Spacer(Modifier.size(5.dp))
                inputTextFiled(
                    TextUtils.addSeparator(salePricePerItem),
                    onValueChange = {
                        salePricePerItem = TextUtils.onlyNumberString(it)
                    },
                    label ="قیمت فروش هر آیتم",
                    modifier = Modifier.weight(1f),
                )
                Spacer(Modifier.size(5.dp))
                dropDownTextFiled(
                    Modifier.weight(1f),
                    controller.getFinanciers(),
                    label = "صاحب کالا",
                    onItemSelected = {
                        owner = it
                    },
                    enabled = controller.getFinanciers().size > 1,
                    readOnly = true
                )
                Spacer(Modifier.size(5.dp))
                inputTextFiled(
                    date,
                    onValueChange = {
                        date = it
                    },
                    label ="تاریخ",
                    modifier = Modifier.weight(1f),
                )
            }
            Spacer(Modifier.size(5.dp))
            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = {
                    controller.addProduct(
                        title,
                        if(numberOfPack.isNotEmpty() && numberOfItem.isNotEmpty()) numberOfPack.toInt()*numberOfItem.toInt() else 0,
                        if (purchasePricePerItem.isNotEmpty()) purchasePricePerItem.toDouble() else 0.0,
                        if (salePricePerItem.isNotEmpty()) salePricePerItem.toDouble() else 0.0,
                        owner?.id?:""
                    )
                }){
                Text("اضافه کردن")
            }
        }
    }
}
