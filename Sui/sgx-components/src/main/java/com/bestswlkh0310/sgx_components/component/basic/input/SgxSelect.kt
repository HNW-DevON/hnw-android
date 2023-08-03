package com.bestswlkh0310.sgx_components.component.basic.input

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.bestswlkh0310.sgx_components.component.basic.Divider
import com.bestswlkh0310.sgx_components.modifier.sgxClickable
import com.bestswlkh0310.sgx_components.theme.Body1
import com.bestswlkh0310.sgx_components.theme.Body3
import com.bestswlkh0310.sgx_components.theme.SgxColor
import com.bestswlkh0310.sgx_components.theme.SgxTheme
import com.bestswlkh0310.sgx_components.theme.IcLeftArrow

/**
 * Sgx Select, can select item
 *
 * @param itemList list of item, select in here!
 * @param hint select guide
 * @param modifier modifier
 * @param onValueChange when value change callback
 * @param focusColor color when focus to this input area
 * @param enabled input area enabled state
 * @param isError error state, write condition!
 * @param errorMessage message to guide error, placed bottom
 * @param textColor color of text
 * @param textStyle style of text
 * @param rippleColor rippleColor
 * @param rippleEnable rippleEnable
 * @param bounded bounded
 * @param onItemClickListener when click item callback(item name)
 */
@Composable
fun SgxSelector(
    itemList: List<String>,
    hint: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    focusColor: Color = SgxColor.MainColor400,
    enabled: Boolean = true,
    isError: Boolean = false,
    errorMessage: String = "",
    textColor: Color = SgxTheme.color.Gray500,
    textStyle: TextStyle = SgxTheme.typography.title1,
    rippleColor: Color = Color.Unspecified,
    rippleEnable: Boolean = false,
    bounded: Boolean = true,
    onItemClickListener: (String) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val focusRequester = remember { FocusRequester() }

    Column {
        SgxInput(
            value = selectedItem,
            onValueChange = {
                selectedItem = it
                onValueChange(it)
            },
            hint = hint,
            modifier = modifier
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                }
                .focusRequester(focusRequester)
                .sgxClickable(
                    rippleColor = rippleColor,
                    rippleEnable = rippleEnable,
                    bounded = bounded
                ) {
                    if (enabled) {
                        expanded = !expanded
                        focusRequester.requestFocus()
                    }
                },
            isError = isError,
            errorMessage = errorMessage,
            textColor = textColor,
            readOnly = true,
            enabled = false,
            textStyle = textStyle,
            trailingIcon = {
                IcLeftArrow(
                    modifier = Modifier
                        .rotate(if (expanded) 90f else 270f)
                        .size(16.dp),
                    contentDescription = null,
                    tint = if (expanded) focusColor else Color.Unspecified
                )
            }
        )
        Box(
            modifier = Modifier.fillMaxWidth().wrapContentSize(Alignment.TopEnd)
        ) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(200.dp),
            ) {
                itemList.forEachIndexed { index, label ->
                    Column {
                        DropdownMenuItem(onClick = {
                            selectedItem = label
                            expanded = false
                            onItemClickListener(label)
                        }) {
                            Body1(
                                text = label,
                                style = SgxTheme.typography.body1.copy(
                                    fontWeight = FontWeight.Normal
                                ),
                                textColor =
                                if (selectedItem == label)
                                    if (isError)
                                        SgxColor.Error
                                    else
                                        focusColor
                                else
                                    textColor
                            )
                        }
                        if (index != itemList.size - 1)
                            Divider(color = SgxColor.Gray50)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectPreview() {
    val sampleList = listOf("Hello", "Hello2", "Hello3", "Hello4")
    val context = LocalContext.current

    Column(
        Modifier
            .background(color = SgxColor.Background)
            .padding(20.dp)
            .fillMaxSize()
    ) {
        SgxSelector(
            itemList = sampleList,
            onItemClickListener = { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() },
            hint = "Hello World",
        )

        Spacer(modifier = Modifier.height(20.dp))

        val isError = remember { mutableStateOf(false) }
        SgxSelector(
            itemList = sampleList,
            onItemClickListener = {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                isError.value = it == "Hello2"
            },
            hint = "Input Some Text",
            isError = isError.value,
            errorMessage = if (isError.value) "Error Message" else ""
        )

        Spacer(modifier = Modifier.height(20.dp))

        SgxSelector(
            itemList = sampleList,
            onItemClickListener = { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() },
            modifier = Modifier
                .fillMaxWidth(),
            hint = "사이즈 조정 가능",
            focusColor = SgxColor.MainColor500,
        )
    }
}
