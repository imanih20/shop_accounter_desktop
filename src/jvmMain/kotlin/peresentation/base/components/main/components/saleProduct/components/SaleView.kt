package peresentation.base.components.main.components.saleProduct.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.utils.TextUtils
import domain.product.model.Product
import domain.trade.model.ProductTrade
import domain.trade.model.TradeType
import ir.huri.jcal.JalaliCalendar
import kotlinx.coroutines.launch
import peresentation.common.components.dropDownTextFiled
import peresentation.common.components.inputTextFiled
import peresentation.common.components.inputDatePicker
import peresentation.common.components.toast

@Composable
fun saleView(productList: List<Product>, modifier: Modifier = Modifier,onSaveClicked: (ProductTrade)->Unit) {
    val scope = rememberCoroutineScope()
    val toastState = remember { SnackbarHostState() }
    //values
    var product : Product? by rememberSaveable { mutableStateOf(null) }
    var price by rememberSaveable() { mutableStateOf("0") }
    var quantity by rememberSaveable { mutableStateOf("") }
    var totalPrice by rememberSaveable { mutableStateOf("0") }
    var date by remember { mutableStateOf(JalaliCalendar().toString()) }
    var notice by remember { mutableStateOf("") }
    //ui actions
    fun clearInputs(){
        price = ""
        quantity = ""
        product = null
        totalPrice = ""
        date = JalaliCalendar().toString()
    }
    var isTitleError by remember { mutableStateOf(false) }
    var isQuantityError by remember { mutableStateOf(false) }
    var toastError by remember { mutableStateOf(false) }
    //methods
    fun onProductSelected(p: Product?){
        product = p
        price = product?.salePrice?.toString()?: "0"
        if (quantity.isNotEmpty() && price.isNotEmpty())
            totalPrice = (quantity.toDouble()*price.toInt()).toString()
    }
    fun onQuantityChange(q: String){
        quantity = TextUtils.doubleNumberString(q)
        if (quantity.isNotEmpty() && price.isNotEmpty())
            totalPrice = (quantity.toDouble()*price.toInt()).toString()
    }
//    if (LocalWindowSize.current == WindowSize.EXPAND){
    Column(modifier, verticalArrangement = Arrangement.SpaceBetween) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                dropDownTextFiled(
                    Modifier.weight(2.8f),
                    productList,
                    "نام کالا",
                    {
                        onProductSelected(it)
                    },
                    isError = isTitleError
                )
                Spacer(Modifier.size(5.dp))
                inputTextFiled(
                    quantity,
                    {
                        onQuantityChange(it)
                    },
                    Modifier.weight(1f),
                    label = "تعداد",
                    isError = isQuantityError
                )
                Spacer(Modifier.size(5.dp))
                inputTextFiled(
                    TextUtils.addSeparator(TextUtils.onlyNumberInput(price)),
                    {},
                    Modifier
                        .weight(2f),
                    label = "قیمت کالا",
                    readOnly = true
                )
            }
            Spacer(Modifier.size(5.dp))
            Row {
                inputTextFiled(
                    TextUtils.addSeparator(TextUtils.onlyNumberInput(totalPrice)),
                    {},
                    Modifier.weight(2f),
                    label = "قیمت کل",
                    readOnly = true
                )
                Spacer(Modifier.size(5.dp))
                inputDatePicker {
                    date = it
                }
            }
            Spacer(Modifier.size(5.dp))
            Button({
                    isTitleError = product==null
                    isQuantityError = quantity.isEmpty()
                    if (isTitleError||isQuantityError) {
                        toastError = true
                        notice = "فیلد های قرمز را پر کنید"
                    } else {
                        val profit = product?.let {
                            (it.salePrice - it.purchasePrice) * quantity.toDouble()
                        } ?: 0
                        if (((product?.quantity ?:0.0) - quantity.toDouble())< 0) {
                            toastError = true
                            notice = "تعداد فروش از تعداد کالاهای موجود بیشتر است."
                        }else {
                            val trade = ProductTrade(
                                title = product?.title ?: "",
                                type = TradeType.SALE,
                                totalPrice = totalPrice.toDouble().toInt(),
                                date = date,
                                quantity = quantity.toDouble(),
                                profit = profit.toInt(),
                                owner = product?.owner ?: ""
                            )
                            onSaveClicked(trade)
                            clearInputs()
                            toastError = false
                            notice = "با موفقیت ثبت شد"
                        }
                    }
                    scope.launch {
                        toastState.showSnackbar(notice)
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ){
                Text("ثبت")
            }
        }
        toast(toastState,toastError)
    }
//    } else {
//        Column(modifier,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Row {
//                dropDownTextFiled(
//                    Modifier.weight(2f),
//                    controller.getAllProducts(),
//                    "نام کالا",
//                    {
//                        onProductSelected(it)
//                    }
//                )
//                Spacer(Modifier.size(5.dp))
//                inputTextFiled(
//                    quantity,
//                    {
//                        onQuantityChange(it)
//                    },
//                    Modifier.weight(1f),
//                    label = "تعداد",
//                )
//                Spacer(Modifier.size(5.dp))
//                inputTextFiled(
//                    TextUtils.addSeparator(price),
//                    {},
//                    Modifier
//                        .weight(2f),
//                    label = "قیمت کالا",
//                    readOnly = true
//                )
//                Spacer(Modifier.size(5.dp))
//                inputTextFiled(
//                    TextUtils.addSeparator(discount),
//                    {},
//                    Modifier
//                        .weight(2f),
//                    label = "تخفیف",
//                    readOnly = true
//                )
//                Spacer(Modifier.size(5.dp))
//                inputTextFiled(
//                    TextUtils.addSeparator(totalPrice),
//                    {},
//                    Modifier.weight(2f),
//                    label = "قیمت کل",
//                    readOnly = true
//                )
//                Spacer(Modifier.size(5.dp))
//                inputTextFiled(
//                    date,
//                    {
//                        date = it
//                    },
//                    Modifier.weight(2f),
//                    label = "تاریخ",
//                )
//            }
//            Spacer(Modifier.size(5.dp))
//            Button(
//                {},
//                modifier = Modifier.align(Alignment.End)
//            ){
//                Text("ثبت")
//            }
//        }
//    }

}
