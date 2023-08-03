package com.bestswlkh0310.sgx_components.component.basic.input

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bestswlkh0310.sgx_components.foundation.Text
import com.bestswlkh0310.sgx_components.theme.SgxColor
import com.bestswlkh0310.sgx_components.theme.IcHome
import com.bestswlkh0310.sgx_components.theme.IcSearch
import com.bestswlkh0310.sgx_components.theme.Label2
import com.bestswlkh0310.sgx_components.theme.LocalContentColor
import com.bestswlkh0310.sgx_components.theme.SgxTheme

sealed interface InputType {
    object Default : InputType
    object UnFocus : InputType
    object Focus : InputType
    object Error {
        object Default : InputType
        object UnFocus : InputType
        object Focus : InputType
    }
}

/**
 * Dodam Input, can write some text
 *
 * @param value text in field
 * @param hint input guide
 * @param modifier modifier
 * @param isError error state, write condition!
 * @param errorMessage message to guide error state, placed bottom
 * @param enabled input area enabled state
 * @param textColor color of text
 * @param singleLine just write one line?
 * @param maxLines count lines
 * @param textStyle style of text
 * @param focusColor color when focus to this input area
 * @param readOnly just read?
 * @param trailingIcon icon placed end
 * @param visualTransformation visualTransformation
 * @param keyboardOptions keyboardOptions
 * @param keyboardActions keyboardActions
 * @param onValueChange when value change callback
 */
@Composable
fun SgxInput(
    value: String,
    hint: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String = "",
    enabled: Boolean = true,
    textColor: Color = SgxTheme.color.Gray500,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    textStyle: TextStyle = SgxTheme.typography.title1,
    focusColor: Color = SgxTheme.color.MainColor400,
    readOnly: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit,
) {
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor, fontWeight = FontWeight.Medium))
    val focusRequester by remember { mutableStateOf(FocusRequester()) }

    var isFocus by remember { mutableStateOf(false) }
    var currentInputType: InputType by remember { mutableStateOf(if (isError) InputType.Error.UnFocus else InputType.Default) }

    Column {
        currentInputType = focusStateAsInputType(isFocus, value, isError)

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier
                .background(
                    color = SgxTheme.color.Transparent,
                    shape = RectangleShape
                )
                .focusRequester(focusRequester)
                .onFocusChanged {
                    isFocus = it.isFocused
                },
            enabled = enabled,
            textStyle = mergedTextStyle,
            cursorBrush = SolidColor(focusColor),
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            readOnly = readOnly,
            decorationBox = @Composable { innerTextField ->
                InputDecoration(
                    inputType = currentInputType,
                    hint = hint,
                    innerTextField = innerTextField,
                    focusColor = focusColor,
                    trailingIcon = trailingIcon,
                )
            }
        )

        if (
            (currentInputType == InputType.Error.Default) or
            (currentInputType == InputType.Error.UnFocus) or
            (currentInputType == InputType.Error.Focus)
        ) {
            Spacer(modifier = Modifier.height(3.dp))
            Label2(
                text = errorMessage,
                textColor = getInputColor(
                    focusColor = focusColor,
                    inputType = currentInputType
                )
            )
        }
    }
}

@Composable
private fun InputDecoration(
    inputType: InputType,
    hint: String,
    focusColor: Color,
    innerTextField: @Composable () -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    val r = getInputColor(focusColor, inputType)
    val inputColor = if (r == focusColor) focusColor else SgxTheme.color.Gray200

    val hintOffsetAnimation: Dp by animateDpAsState(
        if (inputType == InputType.Default || inputType == InputType.Error.Default)
            0.dp
        else
            (-22).dp,
    )

    val hintFontSize by animateFloatAsState(
        if (inputType == InputType.Default || inputType == InputType.Error.Default)
            22f
        else
            14f
    )

    val dividerColor = SgxTheme.color.Gray50

    Box(
        modifier = Modifier
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                val y = size.height - strokeWidth / 2
                drawLine(
                    color = if (inputColor == focusColor) focusColor else dividerColor,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = strokeWidth
                )
            },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 10.dp)
            ) {
                Text(
                    text = hint,
                    color = inputColor,
                    style = SgxTheme.typography.title1.copy(
                        fontSize = hintFontSize.sp,
                        lineHeight = (hintFontSize + 10f).sp,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier
                        .offset(y = hintOffsetAnimation)
                )
                innerTextField()
            }

            trailingIcon?.let {
                Spacer(modifier = Modifier.width(7.dp))
                CompositionLocalProvider(
                    LocalContentColor provides inputColor
                ) {
                    trailingIcon()
                }
            }
        }
    }
}

private fun focusStateAsInputType(
    isFocused: Boolean,
    currentValue: String,
    isError: Boolean
): InputType =
    if (isError) {
        if (isFocused) {
            InputType.Error.Focus
        } else if (currentValue.isNotBlank()) {
            InputType.Error.UnFocus
        } else if (currentValue.isBlank()) {
            InputType.Error.Default
        } else {
            InputType.Error.Default
        }
    } else if (isFocused) {
        InputType.Focus
    } else if (currentValue.isNotBlank()) {
        InputType.UnFocus
    } else if (currentValue.isBlank()) {
        InputType.Default
    } else {
        InputType.Default
    }

@Composable
private fun getInputColor(focusColor: Color, inputType: InputType): Color =
    when (inputType) {
        InputType.Default -> SgxTheme.color.Gray500
        InputType.UnFocus -> SgxTheme.color.Gray500
        InputType.Focus -> focusColor
        InputType.Error.Default -> SgxTheme.color.Error
        InputType.Error.Focus -> SgxTheme.color.Error
        InputType.Error.UnFocus -> SgxTheme.color.Error
    }

@Preview(showBackground = true)
@Composable
fun InputPreview() {

    Column(
        Modifier
            .background(color = SgxColor.Background)
            .padding(20.dp)
            .fillMaxSize()
    ) {

        var testValue by remember { mutableStateOf("") }
        SgxInput(
            value = testValue,
            onValueChange = { testValue = it },
            hint = "Hello World",
        )

        Spacer(modifier = Modifier.height(20.dp))

        var testValue2 by remember { mutableStateOf("") }
        SgxInput(
            value = testValue2,
            onValueChange = { testValue2 = it },
            hint = "Input Some Text",
            trailingIcon = { IcSearch(contentDescription = null) },
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
        ) {
            var testValue3 by remember { mutableStateOf("") }
            SgxInput(
                value = testValue3,
                onValueChange = {
                    testValue3 = it
                },
                modifier = Modifier
                    .fillMaxWidth(),
                hint = "사이즈 조정 가능",
                focusColor = SgxColor.MainColor500,
                isError = true,
                errorMessage = "Error Message",
                trailingIcon = { IcHome(contentDescription = null) },
            )
        }
    }
}
