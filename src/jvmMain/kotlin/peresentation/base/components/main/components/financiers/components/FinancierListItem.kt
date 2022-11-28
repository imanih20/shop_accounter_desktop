package peresentation.base.components.main.components.financiers.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.User

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun financierListItem(modifier: Modifier = Modifier,name: String,description: String){
    var activate by remember{ mutableStateOf(false) }
    Card(
        modifier
            .onPointerEvent(PointerEventType.Enter){activate = true}
            .onPointerEvent(PointerEventType.Exit){activate = false},
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
    ) {
        ListItem(
            icon = {
                Box(
                    Modifier
                        .border(1.dp, Color.Black,shape = CircleShape)
                        .padding(10.dp)
                ){
                    Icon(
                        FontAwesomeIcons.Solid.User,
                        "",
                        tint = Color.LightGray,
                        modifier = Modifier
                            .size(35.dp)
                            .background(MaterialTheme.colors.surface, shape = CircleShape)
                    )
                }
            },
            text = { Text(name, fontSize = 19.sp) },
            secondaryText = {Text(description)},
            trailing = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (activate) {
                        Icon(Icons.Filled.Edit, "ویرایش", tint = Color.Green, modifier = Modifier.clickable { })
                        Spacer(Modifier.size(10.dp))
                        Icon(Icons.Filled.Delete, "حذف", tint = Color.Red, modifier = Modifier.clickable { })
                    }
                }
            }
        )
    }
}