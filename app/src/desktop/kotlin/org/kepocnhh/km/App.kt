package org.kepocnhh.km

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.Dispatchers
import org.kepocnhh.km.module.app.Injection
import org.kepocnhh.km.module.foo.FooScreen
import org.kepocnhh.km.provider.Contexts
import org.kepocnhh.km.provider.FinalLocals
import java.io.File

fun main() {
    Env.create(
        injection = Injection(
            contexts = Contexts(
                main = Dispatchers.Main,
                default = Dispatchers.Default,
            ),
            locals = FinalLocals(
                root = File(System.getProperty("user.home")).resolve(".km"),
            ),
        ),
    )
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
