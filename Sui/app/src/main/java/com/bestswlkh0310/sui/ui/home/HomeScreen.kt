package com.bestswlkh0310.sui.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bestswlkh0310.sgx_components.component.basic.button.SgxRoundedButton
import com.bestswlkh0310.sgx_components.modifier.sgxClickable
import com.bestswlkh0310.sgx_components.theme.Body1
import com.bestswlkh0310.sgx_components.theme.Label2
import com.bestswlkh0310.sgx_components.theme.SgxColor.Gray100
import com.bestswlkh0310.sgx_components.theme.SgxColor.Gray50
import com.bestswlkh0310.sgx_components.theme.SgxTheme
import com.bestswlkh0310.sgx_components.theme.Title2
import com.bestswlkh0310.sui.data.res.CompResponse

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val a by viewModel.state.collectAsState()
    var iss by remember {
        mutableStateOf(false)
    }


    val e by viewModel.stat.collectAsState()

    if (iss) {
        Column(
            Modifier
                .background(Gray50)
                .fillMaxHeight()
        ) {
            HomeDetail(e) {
                iss = false
            }
        }
    }

    LazyColumn {
        items(a) {
            HomeListItem(title = it.d, description = it.e, icon = it.i) {
                viewModel.get(it)
                iss = true
            }
        }
    }
}

@Composable
fun HomeListItem(title: String, description: String, icon: Int, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .background(Color.White)
            .sgxClickable {
                onClick(title)
            }
    ) {
        Spacer(modifier = Modifier.height(19.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(22.dp))
            Title2(text = title)
        }
        Spacer(modifier = Modifier.height(13.dp))

        Row(
//            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(22.dp))
            Image(painter = painterResource(id = icon), contentDescription = null)
            Spacer(modifier = Modifier.width(14.dp))
            Body1(text = description, style = SgxTheme.typography.body1.copy(color = Gray100))
        }
    }
}

@Composable
fun HomeDetail(list: List<CompResponse>, callback: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        SgxRoundedButton(
            text = "뒤로 가기",
            modifier = Modifier
        ) {
            callback()
        }
    }
    Spacer(modifier = Modifier.height(13.dp))

    LazyColumn(
    ) {
        items(list) {
            HomeDetailItem(compResponse = it)
        }
    }

}

@Composable
fun HomeDetailItem(compResponse: CompResponse) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(19.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(18.dp))
            Title2(text = compResponse.name)
        }
        Spacer(modifier = Modifier.height(13.dp))

        Row(
//            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(18.dp))
            Column() {
                Label2(text = compResponse.address.split(" ")[0])
                Label2(text = compResponse.description)
                if (compResponse.goal != "") {
                    Label2(text = compResponse.goal)
                }
                if (compResponse.homepage != "")
                    Label2(text = compResponse.homepage)
            }
        }
        Spacer(modifier = Modifier.height(14.dp))
    }
}