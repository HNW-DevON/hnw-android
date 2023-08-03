package com.bestswlkh0310.sgx_components.component.set.navtab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bestswlkh0310.sgx_components.component.basic.Surface
import com.bestswlkh0310.sgx_components.theme.SgxColor
import com.bestswlkh0310.sgx_components.theme.SgxTheme
import com.bestswlkh0310.sgx_components.theme.IcHome
import com.bestswlkh0310.sgx_components.theme.IcMeal
import com.bestswlkh0310.sgx_components.theme.IcSong
import com.bestswlkh0310.sgx_components.theme.IcUser
import com.bestswlkh0310.sgx_components.theme.contentColorFor

private val NavBarHeight = 56.dp

/**
 * Dodam Bottom NavBar, just bar
 *
 * @param modifier modifier
 * @param backgroundColor color of background
 * @param contentColor color of content
 * @param content composable contents in row scope
 */
@Composable
fun SgxNavBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = SgxTheme.color.White,
    contentColor: Color = contentColorFor(backgroundColor = backgroundColor),
    content: @Composable RowScope.() -> Unit,
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(NavBarHeight)
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceBetween,
            content = content
        )
    }
}

@Preview
@Composable
private fun NavBarPreview() {

    var selectedNavTab by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SgxColor.Background)
    ) {
        SgxNavBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
                .clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
        ) {
            SgxNavTab(
                text = "홈",
                icon = { IcHome(contentDescription = null) },
                onClick = { selectedNavTab = 0 },
                selected = selectedNavTab == 0,
            )
            SgxNavTab(
                text = "급식",
                icon = { IcMeal(contentDescription = null) },
                onClick = { selectedNavTab = 1 },
                selected = selectedNavTab == 1,
            )
            SgxNavTab(
                text = "기상송",
                icon = { IcSong(contentDescription = null) },
                onClick = { selectedNavTab = 2 },
                selected = selectedNavTab == 2,
            )
            SgxNavTab(
                text = "내 정보",
                icon = { IcUser(contentDescription = null) },
                onClick = { selectedNavTab = 3 },
                selected = selectedNavTab == 3,
            )
        }
    }
}
