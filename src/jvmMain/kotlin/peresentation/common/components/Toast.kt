package peresentation.common.components

import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun toast(state: SnackbarHostState, isError: Boolean = false) {
    SnackbarHost(state) {
        Snackbar(it, backgroundColor = if (isError) Color.Red else Color.Green)
    }
}