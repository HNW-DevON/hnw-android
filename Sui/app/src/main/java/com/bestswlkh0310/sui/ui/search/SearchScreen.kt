package com.bestswlkh0310.sui.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bestswlkh0310.sgx_components.component.basic.Surface
import com.bestswlkh0310.sgx_components.component.basic.button.SgxMediumRoundedButton
import com.bestswlkh0310.sgx_components.component.basic.input.SgxInput
import com.bestswlkh0310.sgx_components.component.organization.dialog.SgxDialog
import com.bestswlkh0310.sgx_components.modifier.sgxClickable
import com.bestswlkh0310.sgx_components.theme.Body1
import com.bestswlkh0310.sgx_components.theme.IcSearch
import com.bestswlkh0310.sgx_components.theme.Label1
import com.bestswlkh0310.sgx_components.theme.SgxColor.Gray400
import com.bestswlkh0310.sgx_components.theme.SgxTheme
import com.bestswlkh0310.sgx_components.theme.Title2

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val state by viewModel.state.collectAsState()
    val stat by viewModel.stat.collectAsState()
    val clicked by viewModel.clicked.collectAsState()

    var isD by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true) {
        viewModel.init()
    }
    if (isD) {
        SgxDialog(title = clicked!!.name, primaryButton = {
            SgxMediumRoundedButton(text = "확인") {
                isD = false
            }
        }, onDismiss = { isD = false })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .wrapContentWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            SgxInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .background(SgxTheme.color.White),
                value = state,
                onValueChange = { viewModel.updateSearch(it) },
                textStyle = SgxTheme.typography.title1.copy(fontSize = 18.sp),
                hint = "",
                trailingIcon = { IcSearch(contentDescription = null) }
            )
        }


        Spacer(modifier = Modifier.height(10.dp))

        if (state == "") {
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center) {
                Body1(text = "검색해주세용")
                Spacer(modifier = Modifier.height(20.dp))
            }
        } else {
            LazyColumn {
                items(stat) {
                    SearchListItem(title = it.name, description = it.service) {
                        viewModel.getComp(it)
                        isD = true
                    }
                }
            }
        }
    }
}

@Composable
fun SearchListItem(title: String, description: String, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .background(Color.White)
            .sgxClickable {
                onClick(title)
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 13.dp)
                .padding(top = 6.dp)
        ) {
            Title2(text = title)
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Label1(text = description, style = SgxTheme.typography.label1.copy(color = Gray400, fontWeight = FontWeight.Normal))
            }
        }
    }
}