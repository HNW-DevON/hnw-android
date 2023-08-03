package com.bestswlkh0310.sui.ui.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bestswlkh0310.sgx_components.component.basic.input.SgxInput
import com.bestswlkh0310.sgx_components.modifier.sgxClickable
import com.bestswlkh0310.sgx_components.theme.Body1
import com.bestswlkh0310.sgx_components.theme.Headline1
import com.bestswlkh0310.sgx_components.theme.IcDefault
import com.bestswlkh0310.sgx_components.theme.SgxTheme
import com.bestswlkh0310.sgx_components.theme.SgxTypography
import com.bestswlkh0310.sui.R


@Composable
fun Login(
    onClickJoin: () -> Unit,
    viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onClickNext: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val success by viewModel.isSuccess.collectAsState()
    if (success) {
        onClickNext()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(65.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(36.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_good),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(49.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(36.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_login_title),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(41.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 25.dp)
            ) {
                SgxInput(value = state.id, hint = "아이디", onValueChange = { viewModel.updateId(it) })
                Spacer(modifier = Modifier.height(30.dp))
                SgxInput(value = "*".repeat(state.pw.length), hint = "비밀번호", onValueChange = { viewModel.updatePw(it) })
            }

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.BottomCenter
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                ) {
                    Spacer(modifier = Modifier.width(43.dp))
                    Column() {
                        Headline1(text = "로그인", style = SgxTypography.headline1.copy(fontWeight = FontWeight.Bold))
                        Spacer(modifier = Modifier.height(41.dp))
                        Body1(text = "회원가입", textColor = SgxTheme.color.MainColor, onClick = onClickJoin)
                    }
                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Box(
                            modifier = Modifier
                                .size(70.dp)
                                .clip(CircleShape)
                                .background(SgxTheme.color.MainColor)
                                .sgxClickable(rippleEnable = true) {
                                    viewModel.login()
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            IcDefault(contentDescription = null, id = R.drawable.ic_arrow, tint = SgxTheme.color.White)
                        }
                        Spacer(modifier = Modifier.width(35.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(47.dp))
        }
    }
}