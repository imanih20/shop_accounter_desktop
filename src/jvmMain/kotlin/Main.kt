// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import common.appModule
import common.consts.typography
import common.utils.DbUtils
import common.utils.WindowSize
import data.financier.financierModule
import data.product.productModule
import data.statistic.statisticModule
import data.trade.tradeModule
import org.koin.core.context.startKoin
import peresentation.base.baseCompose

val LocalWindowSize = compositionLocalOf { WindowSize.COMPACT }

@Composable
@Preview
fun app(windowSize: WindowSize) {
    MaterialTheme(typography = typography) {
        CompositionLocalProvider(
            LocalLayoutDirection provides LayoutDirection.Rtl,
            LocalWindowSize provides windowSize
        ) {
            baseCompose()
        }
    }
}

fun main() = application {
    startKoin {
        printLogger()
        modules(productModule, financierModule, tradeModule, appModule, statisticModule)
    }
    DbUtils.configureDb()
//    val windowState = rememberWindowState(size = DpSize(1100.dp, 800.dp))
    val windowState = rememberWindowState(placement = WindowPlacement.Maximized)
    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        title = "حسابدار"
    ) {
        app(WindowSize.basedOnWidth(windowState.size.width))
    }
}
