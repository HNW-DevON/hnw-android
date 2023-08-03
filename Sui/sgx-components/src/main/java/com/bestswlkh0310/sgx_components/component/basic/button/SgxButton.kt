package com.bestswlkh0310.sgx_components.component.basic.button

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bestswlkh0310.sgx_components.theme.SgxTheme
import com.bestswlkh0310.sgx_components.theme.IcDinner3D
import com.bestswlkh0310.sgx_components.theme.IcHome
import com.bestswlkh0310.sgx_components.theme.Label1
import com.bestswlkh0310.sgx_components.theme.SgxTheme.shape
import com.bestswlkh0310.sgx_components.theme.Title2
import com.bestswlkh0310.sgx_components.utlis.SgxDimen

@Composable
fun SgxRoundedButton(
    text: String,
    textColor: Color = SgxTheme.color.White,
    modifier: Modifier = Modifier,
    iconLeft: @Composable (() -> Unit)? = null,
    iconRight: @Composable (() -> Unit)? = null,
    shape: Shape = SgxTheme.shape.medium,
    type: ButtonType = ButtonType.Primary,
    enable: Boolean = true,
    isMaxWidth: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit,
) {
    val isClicked = interactionSource.collectIsPressedAsState().value
    val textAlpha by animateFloatAsState(
        targetValue = if (isClicked) 0.8f else 1f,
        animationSpec = tween(200)
    )
    val scale by animateFloatAsState(if (isClicked) 0.98f else 1f)

    Button(
        onClick = onClick,
        modifier = modifier
            .scale(scale),
        iconLeft = iconLeft,
        iconRight = iconRight,
        shape = shape,
        type = if (enable) type else ButtonType.Disable,
        enable = enable,
        interactionSource = interactionSource,
    ) {
        Title2(
            text = text,
            textColor = textColor,
            modifier = if (isMaxWidth) Modifier
                .fillMaxWidth()
                .alpha(textAlpha) else Modifier.alpha(textAlpha),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SgxSmallRoundedButton(
    text: String,
    modifier: Modifier = Modifier,
    iconLeft: @Composable (() -> Unit)? = null,
    iconRight: @Composable (() -> Unit)? = null,
    type: ButtonType = ButtonType.Primary,
    enable: Boolean = true,
    onClick: () -> Unit,
) {
    SgxRoundedButton(
        text = text,
        modifier = modifier,
        iconLeft = iconLeft,
        iconRight = iconRight,
        shape = SgxTheme.shape.small,
        type = type,
        enable = enable,
        onClick = onClick,

    )
}

@Composable
fun SgxMediumRoundedButton(
    text: String,
    modifier: Modifier = Modifier,
    iconLeft: @Composable (() -> Unit)? = null,
    iconRight: @Composable (() -> Unit)? = null,
    type: ButtonType = ButtonType.Primary,
    enable: Boolean = true,
    onClick: () -> Unit,
) {
    SgxRoundedButton(
        text = text,
        modifier = modifier,
        iconLeft = iconLeft,
        iconRight = iconRight,
        shape = SgxTheme.shape.medium,
        type = type,
        enable = enable,
        onClick = onClick,
    )
}

@Composable
fun SgxLargeRoundedButton(
    text: String,
    modifier: Modifier = Modifier,
    iconLeft: @Composable (() -> Unit)? = null,
    iconRight: @Composable (() -> Unit)? = null,
    type: ButtonType = ButtonType.Primary,
    enable: Boolean = true,
    onClick: () -> Unit,
) {
    SgxRoundedButton(
        text = text,
        modifier = modifier,
        iconLeft = iconLeft,
        iconRight = iconRight,
        shape = SgxTheme.shape.large,
        type = type,
        enable = enable,
        onClick = onClick,
    )
}

@Composable
fun SgxMaxWidthButton(
    text: String,
    modifier: Modifier = Modifier,
    shape: Shape = SgxTheme.shape.medium,
    type: ButtonType = ButtonType.Primary,
    enable: Boolean = true,
    onClick: () -> Unit,
) {
    SgxRoundedButton(
        text = text,
        modifier = modifier,
        shape = shape,
        type = type,
        enable = enable,
        isMaxWidth = true,
        onClick = onClick,
    )
}


@Preview
@Composable
private fun PreviewSgxButton() {
    Column(
        modifier = Modifier
            .padding(SgxDimen.ScreenSidePadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        SgxLargeRoundedButton(
            onClick = {},
            iconLeft = { IcHome(contentDescription = null) },
            iconRight = { IcDinner3D(contentDescription = null) },
            text = "안녕하세요"
        )
        SgxMediumRoundedButton(onClick = {}, text = "로그인", iconLeft = { IcHome(contentDescription = null) })
        SgxSmallRoundedButton(onClick = {}, text = "Button")
        SgxMaxWidthButton(text = "SSSS") {}
    }
}
@Composable
fun SgxGrayButton(
    text: String,
    modifier: Modifier = Modifier,
    iconLeft: @Composable (() -> Unit)? = null,
    iconRight: @Composable (() -> Unit)? = null,
    type: ButtonType = ButtonType.PrimaryVariant,
    enable: Boolean = true,
    onClick: () -> Unit,
) {
    SgxRoundedButton(
        text = text,
        textColor = SgxTheme.color.Gray500,
        modifier = modifier,
        iconLeft = iconLeft,
        iconRight = iconRight,
        shape = SgxTheme.shape.small,
        type = type,
        enable = enable,
        onClick = onClick,
    )
}

/*

@Preview
@Composable
private fun PreviewSgxRadioButton() {
    Column(
        modifier = Modifier
            .padding(SgxDimen.ScreenSidePadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        var radioSelect by remember { mutableStateOf(0) }
        SgxRadioButton(selected = radioSelect == 0) {
            radioSelect = 0
        }
        SgxRadioButton(selected = radioSelect == 1) {
            radioSelect = 1
        }
        SgxRadioButton(selected = radioSelect == 2) {
            radioSelect = 2
        }
        SgxRadioButton(selected = radioSelect == 3) {
            radioSelect = 3
        }
    }
}
*/
