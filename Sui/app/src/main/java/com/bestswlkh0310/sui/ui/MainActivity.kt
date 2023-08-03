package com.bestswlkh0310.sui.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bestswlkh0310.sgx_components.component.basic.Surface
import com.bestswlkh0310.sgx_components.component.set.tab.DividerPosition
import com.bestswlkh0310.sgx_components.component.set.tab.SgxTab
import com.bestswlkh0310.sgx_components.component.set.tab.SgxTabs
import com.bestswlkh0310.sgx_components.theme.Headline1
import com.bestswlkh0310.sgx_components.theme.IcDefault
import com.bestswlkh0310.sgx_components.theme.IcHome
import com.bestswlkh0310.sgx_components.theme.IcSearch
import com.bestswlkh0310.sgx_components.theme.IcUser
import com.bestswlkh0310.sgx_components.theme.SgxTheme
import com.bestswlkh0310.sgx_components.theme.Title1
import com.bestswlkh0310.sui.R
import com.bestswlkh0310.sui.ui.com.ComScreen
import com.bestswlkh0310.sui.ui.home.HomeScreen
import com.bestswlkh0310.sui.ui.my.MyScreen
import com.bestswlkh0310.sui.ui.onboard.OnBoardScreen
import com.bestswlkh0310.sui.ui.util.Application
import com.bestswlkh0310.sui.ui.util.PreferenceManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SgxTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = SgxTheme.color.Background
                ) {
                    Application.prefs = PreferenceManager(applicationContext)

                    var isLogin by remember { mutableStateOf(true)/*mutableStateOf(Application.prefs.isLogin)*/ }
                    if (isLogin) {
                        App()
                    } else {
                        OnBoardScreen() {
                            isLogin = true
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun App() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val selectedTab = remember { mutableStateOf(1) }

        Column(
            modifier = Modifier
                .weight(1f)
                .background(SgxTheme.color.Gray50)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(125.dp)) {
                Spacer(
                    modifier = Modifier.height(27.dp)
                )
                Row(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Spacer(modifier = Modifier.width(29.dp))
                    Image(
                        painter = painterResource(R.drawable.ic_main_title),
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.width(33.dp))
                    Headline1(text = ge(selectedTab.value), style = SgxTheme.typography.headline1.copy(fontWeight = FontWeight.Bold), textColor = SgxTheme.color.MainColor)
                }
            }

            if (selectedTab.value == 1) {
                HomeScreen()
            } else if (selectedTab.value == 2) {
                HomeScreen()
            } else if (selectedTab.value == 3) {
                ComScreen()
            } else if (selectedTab.value == 4) {
                MyScreen()
            }
        }

        Box {
            BottomNav(selectedTab) {

            }/*
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                SgxIconButton(
                    modifier = Modifier
                        .scale(1.2f),
                    icon = { IcSearch(contentDescription = null) },
                ) {
                    Log.d("TAG", "캠 - App() called")
                    // todo: cam open
                }
            }*/
        }
    }
}

fun ge(s: Int): String {
    return when (s) {
        1 -> "사회 서비스 분야"
        2 -> "검색"
        3 -> "인증"
        4 -> "MY"
        else -> ""
    }
}

@Composable
fun BottomNav(
    selectedTab: MutableState<Int>,
    onClickCam: () -> Unit
) {
    SgxTabs(
        modifier = Modifier.fillMaxWidth()
    ) {
        SgxTab(
            text = "홈",
            selected = selectedTab.value == 1,
            onClick = { selectedTab.value = 1 },
            modifier = Modifier.weight(1f),
            dividerPosition = DividerPosition.Disable,
            icon = {
                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    IcDefault(modifier = Modifier.scale(2f), id = R.drawable.ic_home, contentDescription = null)
                }
            }
        )/*
        Spacer(
            modifier = Modifier
                .width(20.dp)
        )
        Spacer(
            modifier = Modifier
                .width(20.dp)
        )*/
        SgxTab(
            text = "검색",
            selected = selectedTab.value == 2,
            onClick = { selectedTab.value = 2 },
            modifier = Modifier.weight(1f),
            dividerPosition = DividerPosition.Disable,
            icon = {
                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    IcDefault(modifier = Modifier, id = com.bestswlkh0310.components.R.drawable.ic_search, contentDescription = null)
                }
            }
        )
        SgxTab(
            text = "인증",
            selected = selectedTab.value == 3,
            onClick = { selectedTab.value = 3 },
            modifier = Modifier.weight(1f),
            dividerPosition = DividerPosition.Disable,
            icon = {
                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    IcDefault(modifier = Modifier.scale(2f), id = R.drawable.ic_camera, contentDescription = null)
                }
            }
        )
        SgxTab(
            text = "MY",
            selected = selectedTab.value == 4,
            onClick = { selectedTab.value = 4 },
            modifier = Modifier.weight(1f),
            dividerPosition = DividerPosition.Disable,
            icon = {
                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    IcDefault(modifier = Modifier.scale(2f), id = R.drawable.ic_profile, contentDescription = null)
                }
            }
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