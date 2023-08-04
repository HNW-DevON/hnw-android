package com.bestswlkh0310.sui.ui.my

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bestswlkh0310.sgx_components.component.basic.Surface
import com.bestswlkh0310.sgx_components.foundation.Icon
import com.bestswlkh0310.sgx_components.theme.Body1
import com.bestswlkh0310.sgx_components.theme.Headline1
import com.bestswlkh0310.sgx_components.theme.IcDefault
import com.bestswlkh0310.sgx_components.theme.IcUser
import com.bestswlkh0310.sgx_components.theme.Label1
import com.bestswlkh0310.sgx_components.theme.SgxColor.Gray400
import com.bestswlkh0310.sgx_components.theme.SgxColor.Gray50
import com.bestswlkh0310.sgx_components.theme.SgxColor.MainColor
import com.bestswlkh0310.sgx_components.theme.SgxTheme
import com.bestswlkh0310.sgx_components.theme.Title1
import com.bestswlkh0310.sui.R

@Composable
fun MyScreen() {

    Column(
        Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(22.dp))
                    Column {
                        Title1(text = "최미래님")
                        Body1(text = "ahffkdy@gmail.com", textColor = Gray400)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Surface(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(19.dp))
                Row(Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.width(23.dp))
                    Title1(text = "등급")
                }
                Spacer(modifier = Modifier.height(19.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.width(18.dp))
                    Image(painter = painterResource(id = R.drawable.asd), contentDescription = null)
                    Spacer(modifier = Modifier.width(10.dp))
                    Headline1(text = "착소 새싹", style = SgxTheme.typography.headline1.copy(fontWeight = FontWeight.SemiBold, color = MainColor))
                }
                Spacer(modifier = Modifier.height(22.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(id = R.drawable.qwe), contentDescription = null)
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            Label1 (text ="660 포인트 추가 달성 시 착소 줄기 달성!")
                        }
                    }
                }
                Spacer(modifier = Modifier.height(49.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Image(painter = painterResource(id = R.drawable.p), contentDescription = null)
                }
            }
        }
    }
}