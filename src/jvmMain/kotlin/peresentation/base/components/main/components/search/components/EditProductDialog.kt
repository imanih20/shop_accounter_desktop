package peresentation.base.components.main.components.search.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import common.consts.SPACER_SIZE
import common.utils.TextUtils
import domain.financier.model.Financier
import domain.product.model.Product
import peresentation.common.components.dropDownTextFiled
import peresentation.common.components.inputTextFiled

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun editProductDialog(
    onCloseRequest: () -> Unit,
    product: Product,
    financiers: List<Financier>,
    onPositiveButtonClicked: (Product?) -> Unit,
) {
    var title by remember { mutableStateOf(product.title) }
    var isEdited by remember { mutableStateOf(false) }
    var purchasePrice by remember { mutableStateOf(product.purchasePrice.toString()) }
    var salePrice by remember { mutableStateOf(product.salePrice.toString()) }
    var quantity by remember { mutableStateOf(product.quantity.toString()) }
    var owner by remember { mutableStateOf(product.owner) }
    Box(Modifier.wrapContentSize()) {
        AlertDialog(
            {},
            text = {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    Column {
                        Row {
                            inputTextFiled(
                                title,
                                {
                                    title = it
                                    isEdited = true
                                },
                                modifier = Modifier.weight(2f),
                                label = "عنوان"
                            )
                            Spacer(Modifier.size(SPACER_SIZE))
                            inputTextFiled(
                                quantity,
                                {
                                    quantity = TextUtils.doubleNumberString(it)
                                    isEdited = true
                                },
                                modifier = Modifier.weight(1f),
                                label = "تعداد"
                            )
                        }
                        Spacer(Modifier.size(SPACER_SIZE))
                        Row {
                            inputTextFiled(
                                TextUtils.addSeparator(purchasePrice),
                                {
                                    purchasePrice = TextUtils.showNumberString(it)
                                    isEdited = true
                                },
                                label = "قیمت خرید"
                            )
                            Spacer(Modifier.size(SPACER_SIZE))
                            inputTextFiled(
                                TextUtils.addSeparator(salePrice),
                                {
                                    salePrice = TextUtils.showNumberString(it)
                                    isEdited = true
                                },
                                label = "قیمت فروش"
                            )
                            Spacer(Modifier.size(SPACER_SIZE))
                            dropDownTextFiled(
                                Modifier.weight(1f),
                                financiers,
                                label = "صاحب کالا",
                                onItemSelected = {
                                    owner = it?.toString() ?: ""
                                    isEdited = true
                                },
                                enabled = financiers.size > 1,
                            )
                        }
                    }
                }
            },
            confirmButton = {
                TextButton({
                    if (isEdited) {
                        onPositiveButtonClicked(
                            Product(
                                product.id,
                                title,
                                quantity.toDouble(),
                                purchasePrice.toInt(),
                                salePrice.toInt(),
                                owner
                            )
                        )
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
            },
        )
    }
}