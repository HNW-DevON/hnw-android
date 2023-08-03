package com.bestswlkh0310.sgx_components.component.set.appbar

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bestswlkh0310.sgx_components.foundation.Text
import com.bestswlkh0310.sgx_components.modifier.sgxClickable
import com.bestswlkh0310.sgx_components.theme.IcBackArrow
import com.bestswlkh0310.sgx_components.theme.IcDelete
import com.bestswlkh0310.sgx_components.theme.SgxTheme
import com.bestswlkh0310.sgx_components.utlis.SgxDimen

/**
 * Dodam top app bar, navigation and guide
 *
 * @param onStartIconClick action when click start icon
 * @param modifier modifier
 * @param backgroundColor color of background
 * @param startIcon icon placed start, if null place basic back arrow icon, recommend null
 * @param endContents end composable contents in row scope
 * @param title title left to start icon, recommend null
 * @param titleStyle text style to title
 */
@Composable
fun SgxAppBar(
    onStartIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = SgxTheme.color.White,
    startIcon: (@Composable () -> Unit)? = null,
    endContents: (@Composable RowScope.() -> Unit)? = null,
    title: String? = null,
    titleStyle: TextStyle = SgxTheme.typography.title2.copy(fontWeight = FontWeight.Medium),
) {
    Row(
        modifier = modifier
            .background(color = backgroundColor)
            .fillMaxWidth()
            .height(SgxDimen.AppBarHeight)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = SgxDimen.AppBarDefaultContentSpace)
                .sgxClickable {
                    onStartIconClick()
                },
            contentAlignment = Alignment.Center
        ) {
            startIcon?.let {
                it()
            } ?: IcBackArrow(
                modifier = Modifier
                    .size(SgxDimen.AppBarDefaultIconSize),
                tint = SgxTheme.color.Black,
                contentDescription = null,
            )
        }
        title?.let {
            Text(
                text = it,
                style = titleStyle,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        endContents?.let {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = SgxDimen.AppBarDefaultContentSpace)
                    .weight(1f),
                horizontalArrangement = Arrangement.End,
            ) {
                it()
            }
        }
    }
}

@Preview
@Composable
private fun PreviewDodamAppBar() {
    Column {
        SgxAppBar(
            onStartIconClick = { Log.d("PreviewAppBarTest", "Click!") },
            title = "알림",
            endContents = { AppBarSampleEndContents() }
        )
    }
}

@Composable
private fun RowScope.AppBarSampleEndContents() {
    IcDelete(
        contentDescription = null,
        modifier = Modifier
            .align(Alignment.CenterVertically)
            .size(SgxDimen.AppBarDefaultIconSize)
    )
}
