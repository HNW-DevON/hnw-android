package com.bestswlkh0310.sui.ui.onboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bestswlkh0310.sgx_components.component.basic.Surface
import com.bestswlkh0310.sui.ui.join.Join
import com.bestswlkh0310.sui.ui.login.Login

@Composable
fun OnBoardScreen(
    callback: () -> Unit
) {
    var isLogin by remember { mutableStateOf(true) }
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (isLogin) {
            Login(onClickJoin = {
                isLogin = !isLogin
            }) {
                callback()
            }
        } else {
            Join(onClickLogin = {
                isLogin = !isLogin
            }) {
                isLogin = !isLogin
            }
        }
    }
}