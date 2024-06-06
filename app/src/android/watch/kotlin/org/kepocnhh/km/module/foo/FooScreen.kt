package org.kepocnhh.km.module.foo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
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
import java.util.UUID

@Composable
private fun FooScreen(
    items: List<Foo>,
    onAdd: () -> Unit,
    onDelete: (UUID) -> Unit,
    onUpdate: (UUID) -> Unit,
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
        ) {
            items.forEachIndexed { index, item ->
                item(key = item.id) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onUpdate(item.id)
                            }
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f),
                        ) {
                            BasicText(
                                text = "index: $index",
                                style = TextStyle(fontSize = 8.sp),
                            )
                            BasicText(
                                text = item.id.toString(),
                                style = TextStyle(fontSize = 10.sp),
                            )
                            BasicText(
                                text = item.text,
                                style = TextStyle(fontSize = 12.sp),
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        BasicText(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .size(36.dp)
                                .background(Color.Black)
                                .clickable {
                                    onDelete(item.id)
                                }
                                .wrapContentSize(),
                            text = "x",
                            style = TextStyle(color = Color.White),
                        )
                    }
                }
            }
        }
        BasicText(
            modifier = Modifier
                .fillMaxWidth()
                .height(insets.calculateBottomPadding())
                .align(Alignment.BottomCenter)
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable(onClick = onAdd)
                .wrapContentSize(),
            text = "+",
            style = TextStyle(color = Color.White),
        )
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
        FooScreen(
            items = state.items,
            onAdd = {
                logics.add()
            },
            onDelete = { id ->
                logics.delete(id)
            },
            onUpdate = { id ->
                logics.update(id)
            },
        )
    }
}
