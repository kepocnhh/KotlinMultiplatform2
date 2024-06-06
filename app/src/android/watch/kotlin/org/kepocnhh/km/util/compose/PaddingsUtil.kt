package org.kepocnhh.km.util.compose

import android.content.res.Configuration
import android.view.View
import android.view.WindowInsets
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.LayoutDirection

internal fun WindowInsets.toPaddings(
    size: DpSize,
): PaddingValues {
    if (!isRound) TODO()
    if (size.width != size.height) TODO()
    val value = (size.width * (kotlin.math.sqrt(2.0) - 1).toFloat()) / (2 * kotlin.math.sqrt(2.0).toFloat())
    return PaddingValues(all = value)
}

@Composable
internal fun WindowInsets.toPaddings(density: Density = LocalDensity.current): PaddingValues {
    val view = LocalView.current
    return toPaddings(
        size = DpSize(
            height = view.height.px(density = density),
            width = view.height.px(density = density),
        ),
    )
}

@Composable
internal fun PaddingValues.calculateStartPadding(
    configuration: Configuration = LocalConfiguration.current,
): Dp {
    val layoutDirection = when (configuration.layoutDirection) {
        View.LAYOUT_DIRECTION_LTR -> LayoutDirection.Ltr
        View.LAYOUT_DIRECTION_RTL -> LayoutDirection.Rtl
        else -> TODO()
    }
    return calculateStartPadding(layoutDirection = layoutDirection)
}

@Composable
internal fun PaddingValues.calculateEndPadding(
    configuration: Configuration = LocalConfiguration.current,
): Dp {
    val layoutDirection = when (configuration.layoutDirection) {
        View.LAYOUT_DIRECTION_LTR -> LayoutDirection.Ltr
        View.LAYOUT_DIRECTION_RTL -> LayoutDirection.Rtl
        else -> TODO()
    }
    return calculateEndPadding(layoutDirection = layoutDirection)
}
