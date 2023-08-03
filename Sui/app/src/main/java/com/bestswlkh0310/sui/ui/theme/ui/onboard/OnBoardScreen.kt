package com.bestswlkh0310.sui.ui.theme.ui.onboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.bestswlkh0310.sgx_components.component.basic.Surface
import com.bestswlkh0310.sgx_components.theme.IcDefault
import com.bestswlkh0310.sui.R

@Composable
fun OnBoardScreen() {
    val isLogin by remember { mutableStateOf(true) }
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (isLogin) {

        } else {
            IcDefault(contentDescription = null, id = R.drawable)
        }
    }
}