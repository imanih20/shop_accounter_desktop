package peresentation.base.components.main.components.saleProduct.components

import LocalWindowSize
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.utils.TextUtils
import common.utils.WindowSize
import domain.product.model.Product
import peresentation.common.components.dropDownTextFiled
import peresentation.common.components.inputTextFiled
import peresentation.base.components.main.components.saleProduct.SaleProductController

@Composable
fun saleView(controller: SaleProductController, modifier: Modifier = Modifier) {
    //values
    var product : Product? by rememberSaveable { mutableStateOf(null) }
    var price by rememberSaveable() { mutableStateOf("0") }
    var quantity by rememberSaveable { mutableStateOf("") }
    var totalPrice by rememberSaveable { mutableStateOf("0") }
    var date by remember { mutableStateOf("") }
    var discount by remember { mutableStateOf("0") }
    //methods
    fun onProductSelected(p: Product?){
        product = p
        price = product?.salePrice?.toString()?: "0"
        if (quantity.isNotEmpty() && price.isNotEmpty())
            totalPrice = controller.calculateTotalPrice(quantity.toInt(),price.toInt(),discount.toInt()).toString()
    }
    fun onQuantityChange(q: String){
        quantity = TextUtils.onlyNumberString(q)
        if (quantity.isNotEmpty() && price.isNotEmpty())
            totalPrice = controller.calculateTotalPrice(quantity.toInt(),price.toInt(),discount.toInt()).toString()
    }
    if (LocalWindowSize.current == WindowSize.EXPAND){
        Column(modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                dropDownTextFiled(
                    Modifier.weight(2f),
                    controller.getAllProducts(),
                    "نام کالا",
                    {
                        onProductSelected(it)
                    }
                )
                Spacer(Modifier.size(5.dp))
                inputTextFiled(
                    quantity,
                    {
                        onQuantityChange(it)
                    },
                    Modifier.weight(1f),
                    label = "تعداد",
                )
            }
            Spacer(Modifier.size(5.dp))
            Row {
                inputTextFiled(
                    TextUtils.addSeparator(price),
                    {},
                    Modifier
                        .weight(2f),
                    label = "قیمت کالا",
                    readOnly = true
                )
                Spacer(Modifier.size(5.dp))
                inputTextFiled(
                    TextUtils.addSeparator(discount),
                    {},
                    Modifier
                        .weight(2f),
                    label = "تخفیف",
                    readOnly = true
                )
            }
            Spacer(Modifier.size(5.dp))
            Row {
                inputTextFiled(
                    TextUtils.addSeparator(totalPrice),
                    {},
                    Modifier.weight(2f),
                    label = "قیمت کل",
                    readOnly = true
                )
                Spacer(Modifier.size(5.dp))
                inputTextFiled(
                    date,
                    {
                        date = it
                    },
                    Modifier.weight(2f),
                    label = "تاریخ",
                )
            }
            Spacer(Modifier.size(5.dp))
            Button(
                {},
                modifier = Modifier.align(Alignment.End)
            ){
                Text("ثبت")
            }
        }
    } else {
        Column(modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                dropDownTextFiled(
                    Modifier.weight(2f),
                    controller.getAllProducts(),
                    "نام کالا",
                    {
                        onProductSelected(it)
                    }
                )
                Spacer(Modifier.size(5.dp))
                inputTextFiled(
                    quantity,
                    {
                        onQuantityChange(it)
                    },
                    Modifier.weight(1f),
                    label = "تعداد",
                )
                Spacer(Modifier.size(5.dp))
                inputTextFiled(
                    TextUtils.addSeparator(price),
                    {},
                    Modifier
                        .weight(2f),
                    label = "قیمت کالا",
                    readOnly = true
                )
                Spacer(Modifier.size(5.dp))
                inputTextFiled(
                    TextUtils.addSeparator(discount),
                    {},
                    Modifier
                        .weight(2f),
                    label = "تخفیف",
                    readOnly = true
                )
                Spacer(Modifier.size(5.dp))
                inputTextFiled(
                    TextUtils.addSeparator(totalPrice),
                    {},
                    Modifier.weight(2f),
                    label = "قیمت کل",
                    readOnly = true
                )
                Spacer(Modifier.size(5.dp))
                inputTextFiled(
                    date,
                    {
                        date = it
                    },
                    Modifier.weight(2f),
                    label = "تاریخ",
                )
            }
            Spacer(Modifier.size(5.dp))
            Button(
                {},
                modifier = Modifier.align(Alignment.End)
            ){
                Text("ثبت")
            }
        }
    }

}
