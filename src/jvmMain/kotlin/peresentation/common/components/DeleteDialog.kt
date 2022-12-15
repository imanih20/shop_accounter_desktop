package peresentation.common.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import common.consts.CORNER_RADIUS

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun deleteDialog(onNegativeButtonClicked: () -> Unit, onPositiveButtonClicked: () -> Unit) {
    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl,
    ) {
        AlertDialog(
            {},
            shape = RoundedCornerShape(CORNER_RADIUS),
            modifier = Modifier.width(300.dp),
            text = {
                Box(contentAlignment = Alignment.BottomStart, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "آیا از حذف این آیتم مطمئنید؟",
                        fontSize = 18.sp,
                        overflow = TextOverflow.Visible,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.align(Alignment.BottomEnd)
                    )
                }
            },
            confirmButton = {
                TextButton({
                    onPositiveButtonClicked()
                }) {
                    Text("بله")
                }
            }, dismissButton = {
                TextButton({
                    onNegativeButtonClicked()
                }) {
                    Text("خیر")
                }
            })
    }
//    AlertDialog(visible = visible, onCloseRequest = {onCloseRequest()}, state = state){
//        Column {
//            Text("آیا از حذف این آیتم مطمئنید؟")
//            Row {
//                TextButton({
//                    onPositiveButtonClicked()
//                }){
//                    Text("بله")
//                }
//                Spacer(Modifier.size(15.dp))
//                TextButton({
//                    window.dispose()
//                }){
//                    Text("خیر")
//                }
//            }
//        }
//    }
}