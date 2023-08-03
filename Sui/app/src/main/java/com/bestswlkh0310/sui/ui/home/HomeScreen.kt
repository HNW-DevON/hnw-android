package com.bestswlkh0310.sui.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bestswlkh0310.sgx_components.foundation.Icon
import com.bestswlkh0310.sgx_components.theme.Body1
import com.bestswlkh0310.sgx_components.theme.Title1
import com.bestswlkh0310.sgx_components.theme.Title2

@Composable
fun HomeScreen() {
    
    LazyColumn() {

    }
}

@Composable
fun HomeListItem(title: String, description: String, icon: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(98.dp)
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(22.dp))
            Spacer(modifier = Modifier.height(19.dp))
        }

        Title2(text = title)
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(22.dp))
            Icon(painter = painterResource(id = icon), contentDescription = null)
            Spacer(modifier = Modifier.width(14.dp))
            Body1(text = description)
        }
    }
}