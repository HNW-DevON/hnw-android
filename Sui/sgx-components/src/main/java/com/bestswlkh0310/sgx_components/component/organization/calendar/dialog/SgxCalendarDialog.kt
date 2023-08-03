package com.bestswlkh0310.sgx_components.component.organization.calendar.dialog

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.bestswlkh0310.sgx_components.component.basic.button.ButtonType
import com.bestswlkh0310.sgx_components.component.basic.button.SgxLargeRoundedButton
import com.bestswlkh0310.sgx_components.component.basic.button.SgxMediumRoundedButton
import com.bestswlkh0310.sgx_components.component.organization.calendar.SgxCalendar
import com.bestswlkh0310.sgx_components.modifier.sgxClickable
import com.bestswlkh0310.sgx_components.theme.SgxColor
import com.bestswlkh0310.sgx_components.theme.SgxTheme
import com.bestswlkh0310.sgx_components.theme.Label2
import com.bestswlkh0310.sgx_components.theme.Title1
import java.time.LocalDate

/**
 * Calendar dialog use prompt
 *
 * @param modifier modifier
 * @param buttonType done button type
 * @param secondaryColor color of secondary button
 * @param onDismiss when click anywhere, if cancel action give null
 */
@Composable
fun SgxCalendarDialog(
    modifier: Modifier = Modifier,
    buttonType: ButtonType = ButtonType.Primary,
    secondaryColor: Color = SgxColor.MainColor400,
    onDismiss: (selectedDate: LocalDate?) -> Unit,
) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    Dialog(
        onDismissRequest = { onDismiss(null) }
    ) {

        Box(
            modifier = modifier.background(
                color = SgxTheme.color.White,
                shape = SgxTheme.shape.medium,
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            ) {
                SgxCalendar(
                    schedules = emptyList(),
                    showCategories = false,
                ) { date, _ ->
                    selectedDate = date
                }

                Spacer(modifier = Modifier.height(100.dp))
            }

            Row(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    )
                    .align(Alignment.BottomEnd)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Label2(
                    text = "CANCEL",
                    textColor = secondaryColor,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .sgxClickable {
                            onDismiss(null)
                        }
                )
                Spacer(modifier = Modifier.width(12.dp))

                SgxLargeRoundedButton(
                    text = "DONE",
                    type = buttonType,
                ) {
                    onDismiss(selectedDate)
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSgxCalendarDialog() {
    var showPrompt by remember {
        mutableStateOf(false)
    }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    if (showPrompt) {
        SgxCalendarDialog { date ->
            showPrompt = false
            date?.let {
                selectedDate = it
            }
        }
    }

    Column(Modifier.fillMaxSize()) {
        SgxMediumRoundedButton(text = "Show Prompt") {
            showPrompt = true
        }
        Title1(text = selectedDate.toString())
    }
}
