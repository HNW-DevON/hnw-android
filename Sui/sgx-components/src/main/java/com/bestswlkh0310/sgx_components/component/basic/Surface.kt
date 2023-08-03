package com.bestswlkh0310.sgx_components.component.basic

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.semantics
import com.bestswlkh0310.sgx_components.modifier.sgxClickable
import com.bestswlkh0310.sgx_components.theme.SgxTheme
import com.bestswlkh0310.sgx_components.theme.LocalContentColor
import com.bestswlkh0310.sgx_components.theme.contentColorFor

/**
 * Sgx Clickable Surface
 *
 * @param onClick action when click surface
 * @param modifier modifier
 * @param shape shape of surface basic is RectangleShape
 * @param color color of surface basic is white
 * @param contentColor color of composable contents in surface
 * @param content composable content in surface
 */
@Composable
fun Surface(
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    color: Color = SgxTheme.color.White,
    contentColor: Color = contentColorFor(color),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit,
) {
    val isClicked = interactionSource.collectIsPressedAsState().value
    val overlayBlack = animateFloatAsState(
        targetValue = if (isClicked) 0.05f else 0.0f,
        animationSpec = tween(200)
    ).value

    CompositionLocalProvider(
        LocalContentColor provides contentColor
    ) {
        Box(
            modifier = modifier
                .surface(
                    shape = shape,
                    backgroundColor = color,
                )
                .overlayBlackBackground(alpha = overlayBlack)
                .sgxClickable(
                    onClick = onClick,
                    interactionSource = interactionSource
                ),
        ) {
            content()
        }
    }
}


fun Modifier.overlayBlackBackground(alpha: Float): Modifier = this.then(
    Modifier.background(Color.Black.copy(alpha = alpha))
)


/**
 * Sgx Basic Surface
 *
 * @param modifier modifier
 * @param shape shape of surface basic is RectangleShape
 * @param color color of surface basic is white
 * @param contentColor color of composable contents in surface
 * @param content composable content in surface
 */
@Composable
fun Surface(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    color: Color = SgxTheme.color.White,
    contentColor: Color = contentColorFor(color),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
    ) {
        Box(
            modifier = modifier
                .surface(
                    shape = shape,
                    backgroundColor = color
                )
                .semantics(mergeDescendants = false) {}
                .pointerInput(Unit) {},
            propagateMinConstraints = true
        ) {
            content()
        }
    }
}

fun Modifier.surface(
    shape: Shape,
    backgroundColor: Color,
) = this
    .background(color = backgroundColor, shape = shape)
    .clip(shape)
