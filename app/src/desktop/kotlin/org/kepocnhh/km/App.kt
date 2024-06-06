package org.kepocnhh.km

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.kepocnhh.km.module.foo.FooScreen

fun main() {
    Env.create()
    application {
        Window(
            state = rememberWindowState(
                size = DpSize(width = 640.dp, height = 480.dp),
            ),
            resizable = false,
            onCloseRequest = ::exitApplication,
        ) {
            FooScreen()
        }
    }
}
