package org.kepocnhh.km.module.foo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.kepocnhh.km.Env
import org.kepocnhh.km.entity.Foo
import org.kepocnhh.km.util.compose.LazyColumn
import org.kepocnhh.km.util.compose.toPaddings

@Composable
private fun FooScreen(
    items: List<Foo>,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        val insets = LocalView.current.rootWindowInsets.toPaddings()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 8.dp),
        ) {
            items.forEachIndexed { index, item ->
                item(key = item.id) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        BasicText(
                            modifier = Modifier.padding(start = 8.dp),
                            text = "$index] id:",
                            style = TextStyle(fontSize = 10.sp),
                        )
                        BasicText(
                            modifier = Modifier,
                            text = item.id.toString(),
                            style = TextStyle(fontSize = 12.sp, fontFamily = FontFamily.Monospace),
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        BasicText(
                            modifier = Modifier.padding(start = 8.dp),
                            text = "text:",
                            style = TextStyle(fontSize = 10.sp),
                        )
                        BasicText(
                            modifier = Modifier,
                            text = item.text,
                            style = TextStyle(fontSize = 12.sp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
internal fun FooScreen() {
    val logics = Env.logics<FooLogics>()
    val state = logics.state.collectAsState().value
    LaunchedEffect(Unit) {
        if (state == null) logics.requestState()
    }
    if (state != null) {
        FooScreen(items = state.items)
    }
}
