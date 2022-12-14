package peresentation.base.components.main.components.addProduct.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common.consts.SPACER_SIZE
import common.utils.PriceUtils
import common.utils.TextUtils
import domain.financier.model.Financier
import domain.product.model.Product
import ir.huri.jcal.JalaliCalendar
import kotlinx.coroutines.launch
import peresentation.common.components.dropDownTextFiled
import peresentation.common.components.inputDatePicker
import peresentation.common.components.inputTextFiled
import peresentation.common.components.toast


@Composable
fun addView(
    modifier: Modifier = Modifier,
    financiers: List<Financier>,
    onSaveButtonClicked: (Product, Int, String) -> Unit
) {
    val scope = rememberCoroutineScope()
    val toastState = remember { SnackbarHostState() }
    //ui text
    var title by remember { mutableStateOf("") }
    var numberOfPack by remember { mutableStateOf("") }
    var numberOfItem by remember { mutableStateOf("") }
    var owner: Financier? by remember { mutableStateOf(financiers.firstOrNull()) }
    var totalPurchasePrice by remember { mutableStateOf("") }
    var purchasePricePerItem by remember { mutableStateOf("0") }
    var salePricePerItem by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(JalaliCalendar().toString()) }

    fun clearInputs() {
        title = ""
        numberOfItem = ""
        numberOfPack = ""
        owner = financiers.firstOrNull()
        totalPurchasePrice = ""
        purchasePricePerItem = ""
        salePricePerItem = ""
        date = JalaliCalendar().toString()
    }
    //ui action
    var isTitleError by remember { mutableStateOf(false) }
    var isNumberOfPackError by remember { mutableStateOf(false) }
    var isNumberOfItemError by remember { mutableStateOf(false) }
    var isTotalPriceError by remember { mutableStateOf(false) }
    var isSalePriceError by remember { mutableStateOf(false) }
    var isFinancierError by remember { mutableStateOf(false) }
    var notice by remember { mutableStateOf("") }
    var toastError by remember { mutableStateOf(false) }
    fun calculatePurchasePrice(){
        if (numberOfPack.isNotEmpty() && numberOfItem.isNotEmpty() && totalPurchasePrice.isNotEmpty())
            purchasePricePerItem = PriceUtils.calculatePrice(
                numberOfPack.toInt(),
                numberOfItem.toDouble(),
                totalPurchasePrice.toInt()
            ).toString()
    }
//    if (LocalWindowSize.current == WindowSize.EXPAND){
    Column(modifier, verticalArrangement = Arrangement.SpaceBetween) {
        Column {
            Row {
                inputTextFiled(
                    title,
                    onValueChange = {
                        title = it
                    },
                    label = "?????? ????????",
                    modifier = Modifier.weight(2f),
                    isError = isTitleError
                )
                Spacer(Modifier.size(SPACER_SIZE))
                inputTextFiled(
                    numberOfPack,
                    onValueChange = {
                        numberOfPack = TextUtils.showNumberString(it)
                        calculatePurchasePrice()
                    },
                    label = "?????????? ????????",
                    modifier = Modifier.weight(1f),
                    isError = isNumberOfPackError
                )
            }
            Spacer(Modifier.size(SPACER_SIZE))
            Row {
                inputTextFiled(
                    numberOfItem,
                    onValueChange = {
                        numberOfItem = TextUtils.doubleNumberString(it)
                        calculatePurchasePrice()
                    },
                    label = "?????????? ???????? ???? ????????",
                    modifier = Modifier.weight(1f),
                    isError = isNumberOfItemError
                )
                Spacer(Modifier.size(SPACER_SIZE))
                inputTextFiled(
                    TextUtils.addSeparator(totalPurchasePrice),
                    onValueChange = {
                        totalPurchasePrice = TextUtils.showNumberString(it)
                        calculatePurchasePrice()
                    },
                    label = "???????? ???? ????????",
                    modifier = Modifier.weight(1f),
                    isError = isTotalPriceError
                )
            }
            Spacer(Modifier.size(SPACER_SIZE))
            Row {
                inputTextFiled(
                    TextUtils.addSeparator(TextUtils.onlyNumberInput(purchasePricePerItem)),
                    onValueChange = {
                    },
                    label = "???????? ???????? ???? ????????",
                    modifier = Modifier.weight(1f),
                    readOnly = true,
                )
                Spacer(Modifier.size(SPACER_SIZE))
                inputTextFiled(
                    TextUtils.addSeparator(salePricePerItem),
                    onValueChange = {
                        salePricePerItem = TextUtils.showNumberString(it)
                    },
                    label = "???????? ???????? ???? ????????",
                    modifier = Modifier.weight(1f),
                    isError = isSalePriceError
                )
            }
            Spacer(Modifier.size(SPACER_SIZE))
            Row {
                dropDownTextFiled(
                    Modifier.weight(1f),
                    financiers,
                    label = "?????? ????",
                    onItemSelected = {
                        owner = it
                    },
                    isError = isFinancierError
                )
                Spacer(Modifier.size(SPACER_SIZE))
                inputDatePicker {
                    date = it
                }
            }
            Spacer(Modifier.size(SPACER_SIZE))
            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = {
                    isTitleError = title.isEmpty()
                    isNumberOfPackError = numberOfPack.isEmpty()
                    isNumberOfItemError = numberOfItem.isEmpty()
                    isTotalPriceError = totalPurchasePrice.isEmpty()
                    isSalePriceError = salePricePerItem.isEmpty()
                    isFinancierError = owner == null

                    val quantity: Double = if (numberOfPack.isNotEmpty() && numberOfItem.isNotEmpty()) {
                        numberOfPack.toDouble() * numberOfItem.toDouble()
                    } else {
                        0.0
                    }

                    val purchasePrice: Int = if (purchasePricePerItem.isNotEmpty()) {
                        purchasePricePerItem.toInt()
                    } else {
                        0
                    }

                    val salePrice: Int = if (salePricePerItem.isNotEmpty()) {
                        salePricePerItem.toInt()
                    } else {
                        0
                    }

                    if (isTitleError || isNumberOfItemError || isNumberOfPackError || isTotalPriceError || isSalePriceError || isFinancierError) {
                        toastError = true
                        notice = "???????? ?????? ???????? ???? ???? ????????"
                    } else {
                        val product = Product(
                            title = title,
                            quantity = quantity,
                            purchasePrice = purchasePrice,
                            salePrice = salePrice,
                            owner = owner?.name ?: ""
                        )
                        onSaveButtonClicked(product, totalPurchasePrice.toInt(), date)
                        toastError = false
                        notice = "???? ???????????? ?????? ????."
                        clearInputs()
                    }
                    scope.launch {
                        toastState.showSnackbar(notice)
                    }
                }) {
                Text("??????")
            }
        }
        toast(toastState, toastError)
    }
//    } else {
//        Column(modifier) {
//            Row {
//                inputTextFiled(
//                    title,
//                    onValueChange = {
//                        title = it
//                    },
//                    label ="?????? ????????",
//                    modifier = Modifier.weight(2f)
//                )
//                Spacer(Modifier.size(5.dp))
//                inputTextFiled(
//                    numberOfPack,
//                    onValueChange = {
//                        numberOfPack = TextUtils.onlyNumberString(it)
//                        if (totalPurchasePrice.isNotEmpty() && numberOfItem.isNotEmpty())
//                            purchasePricePerItem = controller.calculatePrice(numberOfPack.toInt(),numberOfItem.toInt(),totalPurchasePrice.toInt()).toString()
//                    },
//                    label ="?????????? ????????",
//                    modifier = Modifier.weight(1f)
//                )
//                Spacer(Modifier.size(5.dp))
//                inputTextFiled(
//                    numberOfItem,
//                    onValueChange = {
//                        numberOfItem = TextUtils.onlyNumberString(it)
//                        if (totalPurchasePrice.isNotEmpty() && numberOfPack.isNotEmpty())
//                            purchasePricePerItem = controller.calculatePrice(numberOfPack.toInt(),numberOfItem.toInt(),totalPurchasePrice.toInt()).toString()
//                    },
//                    label ="?????????? ???????? ???? ????????",
//                    modifier = Modifier.weight(1f),
//                )
//            }
//            Spacer(Modifier.size(5.dp))
//            Row {
//                inputTextFiled(
//                    TextUtils.addSeparator(totalPurchasePrice),
//                    onValueChange = {
//                        totalPurchasePrice = TextUtils.onlyNumberString(it)
//                        if (numberOfPack.isNotEmpty() && numberOfItem.isNotEmpty())
//                            purchasePricePerItem = controller.calculatePrice(
//                                numberOfPack.toInt(),
//                                numberOfItem.toInt(),
//                                totalPurchasePrice.toInt()
//                            ).toString()
//                    },
//                    label ="???????? ???? ????????",
//                    modifier = Modifier.weight(1f),
//                )
//                Spacer(Modifier.size(5.dp))
//                inputTextFiled(
//                    TextUtils.addSeparator(purchasePricePerItem),
//                    onValueChange = {
//                        purchasePricePerItem = TextUtils.onlyNumberString(it)
//                    },
//                    label = "???????? ???????? ???? ????????",
//                    modifier = Modifier.weight(1f),
//                    readOnly = true,
//                )
//                Spacer(Modifier.size(5.dp))
//                inputTextFiled(
//                    TextUtils.addSeparator(salePricePerItem),
//                    onValueChange = {
//                        salePricePerItem = TextUtils.onlyNumberString(it)
//                    },
//                    label ="???????? ???????? ???? ????????",
//                    modifier = Modifier.weight(1f),
//                )
//                Spacer(Modifier.size(5.dp))
//                dropDownTextFiled(
//                    Modifier.weight(1f),
//                    controller.getFinanciers(),
//                    label = "???????? ????????",
//                    onItemSelected = {
//                        owner = it
//                    },
//                    enabled = controller.getFinanciers().size > 1,
//                    readOnly = true
//                )
//            }
//            Spacer(Modifier.size(5.dp))
//            Button(
//                modifier = Modifier.align(Alignment.End),
//                onClick = {
//                    val product = Product(title = title,
//                        quantity = if(numberOfPack.isNotEmpty() && numberOfItem.isNotEmpty()) numberOfPack.toDouble()*numberOfItem.toDouble() else 0.0,
//                        purchasePrice = if (purchasePricePerItem.isNotEmpty()) purchasePricePerItem.toInt() else 0,
//                        salePrice = if (salePricePerItem.isNotEmpty()) salePricePerItem.toInt() else 0,
//                        ownerId = owner?.id?:0
//                    )
//                    onSaveButtonClicked(product)
//                }){
//                Text("?????????? ????????")
//            }
//        }
//    }
}
