package peresentation.base.components.main.components.addProduct.components

import LocalWindowSize
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.utils.DateUtils
import common.utils.PriceUtils
import common.utils.TextUtils
import common.utils.WindowSize
import domain.financier.model.Financier
import domain.product.model.Product
import ir.huri.jcal.JalaliCalendar
import kotlinx.coroutines.launch
import peresentation.base.components.main.components.addProduct.AddProductController
import peresentation.common.components.dropDownTextFiled
import peresentation.common.components.inputDatePicker
import peresentation.common.components.inputTextFiled


@Composable
fun addView( modifier: Modifier = Modifier,financiers: List<Financier>,onSaveButtonClicked: (Product,Int,String)->Unit) {
    //ui text
    var title by remember { mutableStateOf("") }
    var numberOfPack by remember { mutableStateOf("") }
    var numberOfItem by remember { mutableStateOf("") }
    var owner : Financier? by remember { mutableStateOf(financiers.firstOrNull()) }
    var totalPurchasePrice by remember { mutableStateOf("") }
    var purchasePricePerItem by remember{ mutableStateOf("0") }
    var salePricePerItem by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(JalaliCalendar().toString()) }

    fun clearInputs(){
        title=""
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
//    if (LocalWindowSize.current == WindowSize.EXPAND){
        Column(modifier) {
            Row {
                inputTextFiled(
                    title,
                    onValueChange = {
                        title = it
                    },
                    label ="نام کالا",
                    modifier = Modifier.weight(2f),
                    isError = isTitleError
                )
                Spacer(Modifier.size(5.dp))
                inputTextFiled(
                    numberOfPack,
                    onValueChange = {
                        numberOfPack = TextUtils.onlyNumberString(it)
                        if (totalPurchasePrice.isNotEmpty() && numberOfItem.isNotEmpty())
                            purchasePricePerItem = PriceUtils.calculatePrice(numberOfPack.toInt(),numberOfItem.toInt(),totalPurchasePrice.toInt()).toString()
                    },
                    label ="تعداد بسته",
                    modifier = Modifier.weight(1f),
                    isError = isNumberOfPackError
                )
            }
            Spacer(Modifier.size(5.dp))
            Row {
                inputTextFiled(
                    numberOfItem,
                    onValueChange = {
                        numberOfItem = TextUtils.onlyNumberString(it)
                        if (totalPurchasePrice.isNotEmpty() && numberOfPack.isNotEmpty())
                            purchasePricePerItem = PriceUtils.calculatePrice(numberOfPack.toInt(),numberOfItem.toInt(),totalPurchasePrice.toInt()).toString()
                    },
                    label ="تعداد آیتم هر بسته",
                    modifier = Modifier.weight(1f),
                    isError = isNumberOfItemError
                )
                Spacer(Modifier.size(5.dp))
                inputTextFiled(
                    TextUtils.addSeparator(totalPurchasePrice),
                    onValueChange = {
                        totalPurchasePrice = TextUtils.onlyNumberString(it)
                        if (numberOfPack.isNotEmpty() && numberOfItem.isNotEmpty())
                            purchasePricePerItem = PriceUtils.calculatePrice(
                                numberOfPack.toInt(),
                                numberOfItem.toInt(),
                                totalPurchasePrice.toInt()
                            ).toString()
                    },
                    label ="قیمت کل خرید",
                    modifier = Modifier.weight(1f),
                    isError = isTotalPriceError
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
                    isError = isSalePriceError
                )
            }
            Spacer(Modifier.size(5.dp))
            Row {
                dropDownTextFiled(
                    Modifier.weight(1f),
                    financiers,
                    label = "صاحب کالا",
                    onItemSelected = {
                        owner = it
                    },
                    isError = isFinancierError
                )
                Spacer(Modifier.size(5.dp))
                inputDatePicker{
                    date = it
                }
            }
            Spacer(Modifier.size(5.dp))
            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = {
                    isTitleError = title.isEmpty()
                    isNumberOfPackError = numberOfPack.isEmpty()
                    isNumberOfItemError = numberOfItem.isEmpty()
                    isTotalPriceError = totalPurchasePrice.isEmpty()
                    isSalePriceError = salePricePerItem.isEmpty()
                    isFinancierError = owner == null

                    val quantity : Double = if(numberOfPack.isNotEmpty() && numberOfItem.isNotEmpty()){
                        numberOfPack.toDouble()*numberOfItem.toDouble()
                    } else {
                        0.0
                    }

                    val purchasePrice : Int = if (purchasePricePerItem.isNotEmpty()) {
                        purchasePricePerItem.toInt()
                    }else {
                        0
                    }

                    val salePrice: Int = if (salePricePerItem.isNotEmpty()) {
                        salePricePerItem.toInt()
                    } else {
                        0
                    }

                    if (isTitleError||isNumberOfItemError||isNumberOfPackError||isTotalPriceError||isSalePriceError||isFinancierError){
                        return@Button
                    }
                    val product = Product(title = title,
                        quantity = quantity,
                        purchasePrice = purchasePrice,
                        salePrice = salePrice,
                        owner = owner?.name?:""
                    )
                    onSaveButtonClicked(product,totalPurchasePrice.toInt(),date)
                    clearInputs()
                }){
                Text("ثبت")
            }
        }
//    } else {
//        Column(modifier) {
//            Row {
//                inputTextFiled(
//                    title,
//                    onValueChange = {
//                        title = it
//                    },
//                    label ="نام کالا",
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
//                    label ="تعداد بسته",
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
//                    label ="تعداد آیتم هر بسته",
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
//                    label ="قیمت کل خرید",
//                    modifier = Modifier.weight(1f),
//                )
//                Spacer(Modifier.size(5.dp))
//                inputTextFiled(
//                    TextUtils.addSeparator(purchasePricePerItem),
//                    onValueChange = {
//                        purchasePricePerItem = TextUtils.onlyNumberString(it)
//                    },
//                    label = "قیمت خرید هر آیتم",
//                    modifier = Modifier.weight(1f),
//                    readOnly = true,
//                )
//                Spacer(Modifier.size(5.dp))
//                inputTextFiled(
//                    TextUtils.addSeparator(salePricePerItem),
//                    onValueChange = {
//                        salePricePerItem = TextUtils.onlyNumberString(it)
//                    },
//                    label ="قیمت فروش هر آیتم",
//                    modifier = Modifier.weight(1f),
//                )
//                Spacer(Modifier.size(5.dp))
//                dropDownTextFiled(
//                    Modifier.weight(1f),
//                    controller.getFinanciers(),
//                    label = "صاحب کالا",
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
//                Text("اضافه کردن")
//            }
//        }
//    }
}
