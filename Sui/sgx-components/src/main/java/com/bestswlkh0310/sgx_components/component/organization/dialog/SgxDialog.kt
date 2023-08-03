package com.bestswlkh0310.sgx_components.component.organization.dialog

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
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.bestswlkh0310.sgx_components.component.basic.button.ButtonType
import com.bestswlkh0310.sgx_components.component.basic.button.SgxMediumRoundedButton
import com.bestswlkh0310.sgx_components.theme.Body1
import com.bestswlkh0310.sgx_components.theme.SgxTheme
import com.bestswlkh0310.sgx_components.theme.SgxTypography
import com.bestswlkh0310.sgx_components.theme.Title1

/**
 * Dodam Prompt dialog, set title, butotn, description
 *
 * @param title title
 * @param primaryButton positive button
 * @param onDismiss onClick other place
 * @param modifier modifier
 * @param description prompt desc
 * @param secondaryButton negative button
 */
@Composable
fun SgxDialog(
    title: String,
    primaryButton: @Composable () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
    secondaryButton: @Composable (() -> Unit)? = null,
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = modifier
                .background(
                    color = SgxTheme.color.White,
                    shape = SgxTheme.shape.medium,
                )
                .padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp,
                ),
        ) {
            Title1(text = title, modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(2.dp))
            description?.let {
                Body1(
                    text = it,
                    style = SgxTypography.body1.copy(fontWeight = FontWeight.Normal),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                secondaryButton?.let {
                    it()
                    Spacer(modifier = Modifier.width(16.dp))
                }
                primaryButton()
            }
        }
    }
}

@Preview
@Composable
private fun PreviewDodamPrompt() {
    var showPrompt by remember {
        mutableStateOf(false)
    }

    if (showPrompt) {
        SgxDialog(
            title = "개인정보",
            description = "TestTestTestTestTestTest",
            primaryButton = {
                SgxMediumRoundedButton(text = "네") {
                    showPrompt = false
                }
            },
            secondaryButton = {
                SgxMediumRoundedButton(text = "아니요", type = ButtonType.None) {
                    showPrompt = false
                }
            },
            onDismiss = { showPrompt = false }
        )
    }

    Box(Modifier.fillMaxSize()) {
        SgxMediumRoundedButton(text = "Show Prompt") {
            showPrompt = true
        }
    }
}
