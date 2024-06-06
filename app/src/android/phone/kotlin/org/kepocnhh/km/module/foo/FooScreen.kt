package org.kepocnhh.km.module.foo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import org.kepocnhh.km.BuildConfig
import org.kepocnhh.km.Env
import org.kepocnhh.km.entity.Foo
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
            contentPadding = insets,
        ) {
            items.forEachIndexed { index, item ->
                item(key = item.id) {
                    BasicText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 16.dp),
                        text = "$index] id: ${item.id}\ntext: ${item.text}",
                    )
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
