package com.bestswlkh0310.sui.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bestswlkh0310.sgx_components.component.basic.Surface
import com.bestswlkh0310.sgx_components.component.basic.button.ButtonType
import com.bestswlkh0310.sgx_components.component.basic.button.SgxGrayButton
import com.bestswlkh0310.sgx_components.component.basic.button.SgxLargeRoundedButton
import com.bestswlkh0310.sgx_components.component.basic.button.SgxMaxWidthButton
import com.bestswlkh0310.sgx_components.component.basic.button.SgxMediumRoundedButton
import com.bestswlkh0310.sgx_components.component.basic.button.SgxRoundedButton
import com.bestswlkh0310.sgx_components.component.basic.button.SgxSmallRoundedButton
import com.bestswlkh0310.sgx_components.component.basic.input.SgxInput
import com.bestswlkh0310.sgx_components.component.basic.input.SgxInputArea
import com.bestswlkh0310.sgx_components.component.organization.calendar.dialog.SgxCalendarDialog
import com.bestswlkh0310.sgx_components.component.organization.dialog.SgxDialog
import com.bestswlkh0310.sgx_components.component.set.tab.DividerPosition
import com.bestswlkh0310.sgx_components.component.set.tab.SgxTab
import com.bestswlkh0310.sgx_components.component.set.tab.SgxTabs
import com.bestswlkh0310.sgx_components.theme.IcHome
import com.bestswlkh0310.sgx_components.theme.IcSearch
import com.bestswlkh0310.sgx_components.theme.IcSong
import com.bestswlkh0310.sgx_components.theme.SgxTheme
import com.bestswlkh0310.sgx_components.theme.Title1
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SgxTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = SgxTheme.color.Background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        Box(modifier = Modifier.weight(1f)) {
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
        BottomNav()
    }
}

@Composable
fun BottomNav() {
    var selectedTab2 by remember { mutableStateOf(1) }
    SgxTabs(
        modifier = Modifier.fillMaxWidth()
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
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SgxTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = SgxTheme.color.Background
        ) {
            App()
        }
    }
}

@Composable
fun Dialog() {
    var showPrompt by remember {
        mutableStateOf(false)
    }

    if (showPrompt) {
        SgxDialog(
            title = "동아리 가입 신청",
            description = "수락 하시겠습니까?",
            secondaryButton = {
                SgxMediumRoundedButton(text = "아니요", type = ButtonType.None) {
                    showPrompt = false
                }
            },
            primaryButton = {
                SgxMediumRoundedButton(text = "네") {
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

@Composable
fun De() {
    val a = remember { mutableStateOf("") }
    val b = remember { mutableStateOf("") }
    val c = remember { mutableStateOf("") }
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(9.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SgxInputArea(
                value = c.value,
                hint = "내용을 입력해주세요",
                topLabel = "안녕",
                bottomLabel = "안녕",
                onValueChange = {
                    c.value = it
                },
                modifier = Modifier.fillMaxWidth(),
                isError = c.value == "hello"
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            ) {
                SgxInput(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = a.value, hint = "아이디", onValueChange = {a.value = it})
                Spacer(Modifier.height(14.dp))
                SgxInput(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = b.value, hint = "비밀번호", onValueChange = {b.value = it},
                    isError = b.value == "error test",
                    errorMessage = "dpfjsdfsif")
            }

            SgxRoundedButton(text = "둥글둥글") {}
            SgxSmallRoundedButton(text = "좀 둥금") {}
            SgxMediumRoundedButton(text = "둥금") {}
            SgxLargeRoundedButton(text = "많이둥금") {}
            SgxMaxWidthButton(text = "시작하기") {}
            SgxGrayButton(text = "그레이 버튼") {}
            SgxMaxWidthButton(text = "시작하기", enable = false) {}
        }
    }
}