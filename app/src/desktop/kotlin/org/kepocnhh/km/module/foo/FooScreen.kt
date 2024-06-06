package org.kepocnhh.km.module.foo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import org.kepocnhh.km.Env
import org.kepocnhh.km.entity.Foo
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(
                bottom = 8.dp + 64.dp + 8.dp,
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items.forEachIndexed { index, item ->
                item(key = item.id) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onUpdate(item.id)
                            }
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f),
                        ) {
                            BasicText(
                                text = "$index] id:",
                            )
                            BasicText(
                                text = item.id.toString(),
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            BasicText(
                                text = "text:",
                            )
                            BasicText(
                                text = item.text,
                            )
                        }
                        BasicText(
                            modifier = Modifier
                                .size(48.dp)
                                .align(Alignment.CenterVertically)
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
                .align(Alignment.BottomEnd)
                .padding(bottom = 8.dp, end = 16.dp)
                .size(64.dp)
                .background(Color.Black)
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
