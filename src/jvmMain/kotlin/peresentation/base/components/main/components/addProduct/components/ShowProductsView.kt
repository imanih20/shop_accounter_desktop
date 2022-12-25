package peresentation.base.components.main.components.addProduct.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.consts.CORNER_RADIUS
import common.consts.SPACER_SIZE
import common.utils.TextUtils
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.AngleDown
import compose.icons.fontawesomeicons.solid.AngleUp
import domain.trade.model.ProductTrade
import peresentation.common.components.deleteDialog

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun showProductView(
    purchaseList: List<ProductTrade>,
    modifier: Modifier = Modifier,
    onDateChanged: (String)->Unit,
    onDeleteItemRequest: (ProductTrade) -> Unit
) {
    var trade by remember { mutableStateOf<ProductTrade?>(null) }
    var visible by remember { mutableStateOf(false) }
    val financierTotalPurchases = ProductTrade.getTotalTradeForEachFinancier(purchaseList)
    var totalPrice = 0
    purchaseList.forEach {
        totalPrice += it.totalPrice
    }
    Column(modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
        Box (Modifier.weight(9f)){
            if (visible) deleteDialog({ visible = false }) {
                trade?.let { onDeleteItemRequest(it) }
                visible = false
            }
            val state = rememberLazyListState()
            LazyColumn(
                Modifier.padding(10.dp),
                state = state
            ) {
                stickyHeader {
                    showListHeader(){
                        onDateChanged(it)
                    }
                }
                items(purchaseList) {
                    showListItem(it) { tr ->
                        visible = true
                        trade = tr
                    }
                    Spacer(Modifier.size(SPACER_SIZE))
                }
            }
            VerticalScrollbar(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                adapter = rememberScrollbarAdapter(
                    scrollState = state
                )
            )
        }
        Column(
            Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colors.secondary, shape = RoundedCornerShape(
                        CORNER_RADIUS
                    )
                ).padding(10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            var expended by remember { mutableStateOf(false) }
            val icon = if (expended) FontAwesomeIcons.Solid.AngleUp else FontAwesomeIcons.Solid.AngleDown
            Text("میزان خرید روز: ${TextUtils.addSeparator(totalPrice.toString())}")
            if (totalPrice != 0) {
                Spacer(Modifier.size(SPACER_SIZE))
                if (expended) {
                    LazyColumn(
                        Modifier.fillMaxWidth().padding(10.dp)
                    ) {
                        items(financierTotalPurchases.keys.toList()) {
                            Text("میزان خرید $it: ${TextUtils.addSeparator(financierTotalPurchases[it].toString())}")
                            Spacer(Modifier.size(SPACER_SIZE))
                        }
                    }
                }
                Spacer(Modifier.size(SPACER_SIZE))
                Box(Modifier.size(18.dp).align(Alignment.CenterHorizontally)) {
                    Icon(icon, "نشان دادن جزئیات بیشتر", Modifier.clickable {
                        expended = !expended
                    })
                }
            }
        }

    }
}

