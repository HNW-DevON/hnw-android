package com.bestswlkh0310.sgx_components.component.basic.button

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp
import com.bestswlkh0310.sgx_components.component.basic.Surface
import com.bestswlkh0310.sgx_components.modifier.sgxClickable
import com.bestswlkh0310.sgx_components.theme.SgxTheme
import com.bestswlkh0310.sgx_components.theme.LocalContentColor
import com.bestswlkh0310.sgx_components.theme.contentColorFor

sealed interface ButtonType {
    object Primary : ButtonType
    object PrimaryVariant : ButtonType
    object Disable : ButtonType
    object None: ButtonType
}

/**
 * Sgx 버튼
 *
 * @param onClick when click button
 * @param modifier modifier
 * @param iconLeft left icon
 * @param iconRight right icon
 * @param shape shape of button, basic is medium
 * @param type button type, define color
 * @param enable button enable state
 * @param content composable content in button RowScope
 */
@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconLeft: @Composable (() -> Unit)? = null,
    iconRight: @Composable (() -> Unit)? = null,
    shape: Shape = SgxTheme.shape.medium,
    type: ButtonType = ButtonType.Primary,
    enable: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    Surface(
        modifier = modifier,
        onClick = if (enable) onClick else null,
        shape = shape,
        color = backgroundColorFor(type),
        interactionSource = interactionSource
    ) {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            iconLeft?.let {
                Row(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    iconLeft()
                }
                Spacer(modifier = Modifier.width(8.dp))
            }

            Row(
                modifier = Modifier
                    .padding(vertical = 10.dp)
            ) {
                content()
            }

            iconRight?.let {
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ) {
                    iconRight()
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

private val IconButtonSize = 48.dp

/**
 * Sgx icon button, only have icon
 *
 * @param icon center icon
 * @param modifier modifier
 * @param type button type, define color
 * @param enable button enable state
 * @param onClick when click icon button
 */
@Composable
fun SgxIconButton(
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    type: ButtonType = ButtonType.Primary,
    enable: Boolean = true,
    onClick: () -> Unit,
) {
    val backgroundColor = backgroundColorFor(type)

    val iconColor = contentColorFor(backgroundColor = backgroundColor)

    CompositionLocalProvider(
        LocalContentColor provides iconColor
    ) {
        Layout(
            modifier = modifier.then(
                Modifier
                    .background(backgroundColor, CircleShape)
                    .clip(CircleShape)
                    .sgxClickable(
                        onClick = if (enable) onClick else null,
                    )
            ),
            content = icon
        ) { measurables, constraints ->
            val placeables = measurables.map { measurable ->
                measurable.measure(constraints)
            }

            val boxWidth = IconButtonSize.toPx().toInt()
            val boxHeight = IconButtonSize.toPx().toInt()

            layout(boxWidth, boxWidth) {
                placeables.forEach { placeable ->
                    val x = boxWidth / 2 - placeable.width / 2
                    val y = boxHeight / 2 - placeable.height / 2
                    placeable.placeRelative(x = x, y = y)
                }
            }
        }
    }
}
/*
private val RadioButtonDotSize = 12.dp
private val StrokeWidth = 1.dp
private val RadioButtonSize = 20.dp

@Composable
fun SgxRadioButton(
    selected: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    type: ButtonType = ButtonType.PrimaryVariant,
    onClick: (() -> Unit)?,
) {
    val selectedColor = animateColorAsState(
        if (selected) backgroundColorFor(type) else SgxColor.Gray500
    )

    val dotRadius = animateDpAsState(
        targetValue = if (selected) RadioButtonDotSize / 2 else 0.dp,
        animationSpec = tween(durationMillis = 100)
    )

    val selectableModifier = if (onClick != null) {
        Modifier.selectable(
            selected = selected,
            onClick = onClick,
            enabled = enabled,
            role = Role.RadioButton,
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        )
    } else Modifier

    Canvas(
        modifier
            .then(selectableModifier)
            .wrapContentSize(Alignment.Center)
            .padding(2.dp)
            .requiredSize(RadioButtonDotSize)
    ) {
        val strokeWidth = StrokeWidth.toPx()

        // Outside Circle
        drawCircle(
            selectedColor.value,
            (RadioButtonSize / 2).toPx() - strokeWidth / 2, // 전체의 반 - 테두리 굵기 -> 반지름
            style = Stroke(strokeWidth)
        )

        // Insize Circle -> radius = dotRadius - 테두리 굵기
        if (dotRadius.value > 0.dp) {
            drawCircle(selectedColor.value, dotRadius.value.toPx() - strokeWidth / 2, style = Fill)
        }
    }
}*/

@Composable
private fun backgroundColorFor(type: ButtonType): Color =
    when (type) {
        ButtonType.Primary -> SgxTheme.color.MainColor
        ButtonType.PrimaryVariant -> SgxTheme.color.Gray50
        ButtonType.Disable -> SgxTheme.color.MainColor200
        ButtonType.None -> SgxTheme.color.Error
    }
