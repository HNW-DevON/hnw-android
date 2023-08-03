package com.bestswlkh0310.sgx_components.component.set.tab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.bestswlkh0310.sgx_components.component.basic.Surface
import com.bestswlkh0310.sgx_components.modifier.drawColoredShadow
import com.bestswlkh0310.sgx_components.theme.SgxTheme
import com.bestswlkh0310.sgx_components.theme.IcHome
import com.bestswlkh0310.sgx_components.theme.IcSearch
import com.bestswlkh0310.sgx_components.theme.IcSong
import com.bestswlkh0310.sgx_components.theme.contentColorFor

/**
 * Dodam top tabs, just top bar, can use DodamTab
 *
 * @param modifier modifier,
 * @param backgroundColor color of background
 * @param contentColor color of contents
 * @param content composable contents in row scope
 */
@Composable
fun SgxTabs(
    modifier: Modifier = Modifier,
    backgroundColor: Color = SgxTheme.color.White,
    contentColor: Color = contentColorFor(backgroundColor = backgroundColor),
    content: @Composable RowScope.() -> Unit,
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        modifier = modifier
            .drawColoredShadow(SgxTheme.color.Gray300, 0.4f, 10.dp, 4.dp),
        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .selectableGroup()
                .wrapContentWidth(Alignment.CenterHorizontally),
            content = content,
        )
    }
}

@Preview
@Composable
private fun TabsPreview() {
    Column {
        var selectedTab1 by remember { mutableStateOf(1) }
        SgxTabs(
            modifier = Modifier.fillMaxWidth(),
        ) {
            SgxTab(
                text = "ITEM ONE",
                selected = selectedTab1 == 1,
                onClick = { selectedTab1 = 1 },
                modifier = Modifier.weight(1f),
            )
            SgxTab(
                text = "ITEM TWO",
                selected = selectedTab1 == 2,
                onClick = { selectedTab1 = 2 },
                modifier = Modifier.weight(1f),
            )
            SgxTab(
                text = "ITEM THREE",
                selected = selectedTab1 == 3,
                onClick = { selectedTab1 = 3 },
                modifier = Modifier
                    .weight(1f),
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        var selectedTab2 by remember { mutableStateOf(1) }
        SgxTabs(
            modifier = Modifier.fillMaxWidth(),
        ) {
            SgxTab(
                text = "홈",
                selected = selectedTab2 == 1,
                onClick = { selectedTab2 = 1 },
                modifier = Modifier.weight(1f),
                dividerPosition = DividerPosition.Disable,
                icon = { IcHome(contentDescription = null) },
            )
            SgxTab(
                text = "음악",
                selected = selectedTab2 == 2,
                onClick = { selectedTab2 = 2 },
                modifier = Modifier.weight(1f),
                dividerPosition = DividerPosition.Disable,
                icon = { IcSong(contentDescription = null) },
            )
            SgxTab(
                text = "검색",
                selected = selectedTab2 == 3,
                onClick = { selectedTab2 = 3 },
                dividerPosition = DividerPosition.Disable,
                modifier = Modifier
                    .weight(1f),
                icon = { IcSearch(contentDescription = null) },
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        var selectedTab3 by remember { mutableStateOf(1) }
        SgxTabs(
            modifier = Modifier.fillMaxWidth(),
        ) {
            SgxTab(
                text = "ITEM ONE",
                selected = selectedTab3 == 1,
                onClick = { selectedTab3 = 1 },
                dividerPosition = DividerPosition.Top,
                modifier = Modifier.weight(1f),
                icon = { IcHome(contentDescription = null) },
                showLabel = false,
            )
            SgxTab(
                text = "ITEM TWO",
                selected = selectedTab3 == 2,
                onClick = { selectedTab3 = 2 },
                modifier = Modifier.weight(1f),
                dividerPosition = DividerPosition.Top,
                icon = { IcSong(contentDescription = null) },
                showLabel = false,
            )
            SgxTab(
                text = "ITEM THREE",
                selected = selectedTab3 == 3,
                onClick = { selectedTab3 = 3 },
                dividerPosition = DividerPosition.Top,
                modifier = Modifier
                    .weight(1f),
                icon = { IcSearch(contentDescription = null) },
                showLabel = false,
            )
        }
    }
}
