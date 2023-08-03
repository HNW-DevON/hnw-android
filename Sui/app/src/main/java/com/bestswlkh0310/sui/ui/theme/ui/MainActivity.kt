package com.bestswlkh0310.sui.ui.theme.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bestswlkh0310.sgx_components.component.basic.Surface
import com.bestswlkh0310.sgx_components.component.basic.button.SgxIconButton
import com.bestswlkh0310.sgx_components.component.set.tab.DividerPosition
import com.bestswlkh0310.sgx_components.component.set.tab.SgxTab
import com.bestswlkh0310.sgx_components.component.set.tab.SgxTabs
import com.bestswlkh0310.sgx_components.theme.IcHome
import com.bestswlkh0310.sgx_components.theme.IcSearch
import com.bestswlkh0310.sgx_components.theme.IcUser
import com.bestswlkh0310.sgx_components.theme.SgxTheme
import com.bestswlkh0310.sui.ui.theme.ui.home.HomeScreen
import com.bestswlkh0310.sui.ui.theme.ui.my.MyScreen
import com.bestswlkh0310.sui.ui.theme.util.Application
import com.bestswlkh0310.sui.ui.theme.util.PreferenceManager

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

                    val isLogin by remember { mutableStateOf(Application.prefs.isLogin) }
                    if (isLogin) {
                        App()
                    } else {
                        OnBoarding()
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

        Box(modifier = Modifier
            .weight(1f)) {
            if (selectedTab.value == 1) {
                HomeScreen()
            } else {
                MyScreen()
            }
        }
        Box {
            BottomNav(selectedTab) {
                Log.d("TAG", " - App() called")
            }
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
            }

        }

    }
}

@Composable
fun OnBoarding() {

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
            icon = { IcHome(contentDescription = null) },
        )
        Spacer(
            modifier = Modifier
                .width(20.dp)
        )
        Spacer(
            modifier = Modifier
                .width(20.dp)
        )
        SgxTab(
            text = "MY",
            selected = selectedTab.value == 2,
            onClick = { selectedTab.value = 2 },
            modifier = Modifier.weight(1f),
            dividerPosition = DividerPosition.Disable,
            icon = { IcUser(contentDescription = null) },
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