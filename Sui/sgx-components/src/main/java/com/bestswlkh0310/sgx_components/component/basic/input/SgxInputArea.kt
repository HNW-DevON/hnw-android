package com.bestswlkh0310.sgx_components.component.basic.input

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bestswlkh0310.sgx_components.theme.Body1
import com.bestswlkh0310.sgx_components.theme.Body3
import com.bestswlkh0310.sgx_components.theme.SgxColor
import com.bestswlkh0310.sgx_components.theme.SgxTheme
import com.bestswlkh0310.sgx_components.theme.SgxTypography
import com.bestswlkh0310.sgx_components.theme.SgxTypography.body1

sealed interface InputAreaType {
    object Default : InputAreaType
    object Focus : InputAreaType
    object UnFocus : InputAreaType
    object Error : InputAreaType
}

/**
 * Sgx Input Area, can write multi line
 *
 * @param value text in field
 * @param modifier modifier
 * @param hint input guide
 * @param isError error state, write condition!
 * @param topLabel label, top
 * @param bottomLabel label, bottom
 * @param enabled input area enabled state
 * @param singleLine just write one line?
 * @param maxLines count lines
 * @param textColor color of text
 * @param textStyle style of text
 * @param focusColor color when focus to this input area
 * @param readOnly just read?
 * @param visualTransformation visualTransformation
 * @param keyboardOptions keyboardOptions
 * @param keyboardActions keyboardActions
 * @param onValueChange when value change callback
 */
@Composable
fun SgxInputArea(
    value: String,
    modifier: Modifier = Modifier,
    hint: String = "",
    isError: Boolean = false,
    topLabel: String = "",
    bottomLabel: String = "",
    enabled: Boolean = true,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    textColor: Color = SgxTheme.color.Gray500,
    textStyle: TextStyle = SgxTheme.typography.body1,
    focusColor: Color = SgxTheme.color.MainColor,
    readOnly: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit,
) {
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))
    val focusRequester by remember { mutableStateOf(FocusRequester()) }

    var isFocus by remember { mutableStateOf(false) }

    var currentInputType: InputAreaType by remember {
        mutableStateOf(
            if (isError)
                InputAreaType.Error
            else
                InputAreaType.Default
        )
    }

    Column {
        currentInputType = stateAsInputAreaType(isFocus, value, isError)

        if (topLabel.isNotBlank())
            Body3(
                text = topLabel,
                textColor = getInputAreaColorByType(
                    inputAreaType = currentInputType,
                    focusColor = focusColor
                ),
                style = SgxTypography.body3.copy(fontWeight = FontWeight.Normal)
            )
        Spacer(modifier = Modifier.height(4.dp))

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier
                .width(IntrinsicSize.Max)
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
                InputAreaDecoration(
                    inputAreaType = currentInputType,
                    hint = hint,
                    innerTextField = innerTextField,
                )
            }
        )

        Spacer(modifier = Modifier.height(4.dp))
        if (bottomLabel.isNotBlank())
            Body3(
                text = bottomLabel,
                textColor = getInputAreaColorByType(
                    inputAreaType = currentInputType,
                    focusColor = focusColor
                ),
                style = SgxTypography.body3.copy(fontWeight = FontWeight.Normal)
            )
    }
}

@Composable
private fun InputAreaDecoration(
    inputAreaType: InputAreaType,
    hint: String,
    innerTextField: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .background(
                color = SgxTheme.color.Gray50,
                shape = SgxTheme.shape.small
            )
            .padding(12.dp),
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimatedVisibility(visible = inputAreaType is InputAreaType.Default) {
                Body1(
                    text = hint,
                    style = SgxTheme.typography.body1.copy(fontWeight = FontWeight.Normal),
                    textColor = SgxTheme.color.Gray200
                )
            }
            innerTextField()
        }
    }
}

private fun stateAsInputAreaType(
    isFocused: Boolean,
    currentValue: String,
    isError: Boolean,
): InputAreaType =
    if (isError) {
        InputAreaType.Error
    } else if (isFocused) {
        InputAreaType.Focus
    } else if (currentValue.isBlank()) {
        InputAreaType.Default
    } else {
        InputAreaType.UnFocus
    }

@Composable
private fun getInputAreaColorByType(
    inputAreaType: InputAreaType,
    focusColor: Color,
): Color =
    when (inputAreaType) {
        InputAreaType.Default -> SgxTheme.color.Gray200
        InputAreaType.UnFocus -> SgxTheme.color.Gray200
        InputAreaType.Focus -> focusColor
        InputAreaType.Error -> SgxTheme.color.Error
    }

@Preview(showBackground = true)
@Composable
fun InputAreaPreview() {

    Column(
        Modifier
            .background(color = SgxColor.Background)
            .padding(20.dp)
            .fillMaxSize()
    ) {

        var testValue by remember { mutableStateOf("") }
        SgxInputArea(
            value = testValue,
            modifier = Modifier
                .fillMaxWidth(),
            onValueChange = { testValue = it },
            hint = "Hello World",
        )

        Spacer(modifier = Modifier.height(10.dp))

        var testValue2 by remember { mutableStateOf("") }
        SgxInputArea(
            value = testValue2,
            modifier = Modifier
                .fillMaxWidth(),
            onValueChange = { testValue2 = it },
            topLabel = "Top Label",
            hint = "Input Some Text"
        )

        Spacer(modifier = Modifier.height(10.dp))

        var testValue3 by remember { mutableStateOf("") }
        SgxInputArea(
            value = testValue3,
            onValueChange = { testValue3 = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp),
            topLabel = "Top Label",
            bottomLabel = "Bottom Label",
            hint = "사이즈 조정 가능",
            isError = testValue3 == "Hello",
            focusColor = SgxColor.MainColor500,
        )
    }
}
