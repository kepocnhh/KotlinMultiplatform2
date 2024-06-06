package org.kepocnhh.km

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.kepocnhh.km.module.foo.FooScreen

fun main() {
    Env.create()
    application {
        Window(onCloseRequest = ::exitApplication) {
            FooScreen()
        }
    }
}
