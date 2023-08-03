package com.bestswlkh0310.sgx_components.component.organization.calendar.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bestswlkh0310.sgx_components.component.organization.calendar.toKoreanDateTime
import com.bestswlkh0310.sgx_components.foundation.Text
import com.bestswlkh0310.sgx_components.theme.Body2
import com.bestswlkh0310.sgx_components.theme.SgxTheme

/**
 * Sgx Schedule Item
 *
 * @param schedule schedule
 * @param modifier modifier
 */
@Composable
fun SgxScheduleItem(
    schedule: Schedule,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .width(4.dp)
                .height(16.dp)
                .padding(top = 4.dp)
                .background(color = schedule.category.color, shape = SgxTheme.shape.large)
                .align(Alignment.Top)
        )
        Spacer(modifier = Modifier.width(7.dp))
        Column {
            Body2(text = schedule.title, textColor = SgxTheme.color.Black)
            Text(
                text = "${schedule.startDateTime.toKoreanDateTime()} - ${schedule.endDateTime.toKoreanDateTime()} / ${schedule.category.name}",
                color = SgxTheme.color.Gray500,
                style = SgxTheme.typography.body3.copy(
                    fontSize = 10.sp,
                    lineHeight = 12.sp,
                )
            )
        }
    }
}
