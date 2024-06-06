package org.kepocnhh.km.util.compose

import androidx.compose.animation.core.snap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.BasicSwipeToDismissBox
import androidx.wear.compose.foundation.SwipeToDismissBoxState
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListScope
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.foundation.rememberSwipeToDismissBoxState

@Composable
internal fun LazyColumn(
    modifier: Modifier = Modifier,
    state: ScalingLazyListState = rememberScalingLazyListState(0, 0),
    contentPadding: PaddingValues = PaddingValues(),
    autoCentering: AutoCenteringParams = AutoCenteringParams(0, 0),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(0.dp),
    content: ScalingLazyListScope.() -> Unit,
) {
    ScalingLazyColumn(
        modifier = modifier,
        state = state,
        contentPadding = contentPadding,
        autoCentering = autoCentering,
        verticalArrangement = verticalArrangement,
        content = content,
    )
}

@Composable
internal fun SwipeToDismissBox(
    modifier: Modifier,
    state: SwipeToDismissBoxState = rememberSwipeToDismissBoxState(
        animationSpec = snap(delayMillis = 0),
    ),
    userSwipeEnabled: Boolean = true,
    onDismissed: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    BasicSwipeToDismissBox(
        modifier = modifier,
        state = state,
        userSwipeEnabled = userSwipeEnabled,
        onDismissed = onDismissed,
    ) { isBackground: Boolean ->
        if (!isBackground) content()
    }
}
