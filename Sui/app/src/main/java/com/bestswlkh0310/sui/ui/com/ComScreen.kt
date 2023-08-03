package com.bestswlkh0310.sui.ui.com

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bestswlkh0310.sgx_components.component.basic.Surface
import com.bestswlkh0310.sgx_components.theme.Body1
import com.bestswlkh0310.sgx_components.theme.Headline1
import com.bestswlkh0310.sgx_components.theme.SgxTheme
import com.bestswlkh0310.sgx_components.theme.SgxTypography
import com.bestswlkh0310.sgx_components.theme.Title1

@Composable
fun ComScreen(
    viewModel: ComViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val state = viewModel.state.collectAsState()
        var image by remember { mutableStateOf<Bitmap?>(null) }

        when (state.value.status) {
            CamStatus.Loading -> Loading()
            CamStatus.Complete -> {
                if (state.value.no) {
                    if (image != null) {
                        No(image!!)
                    } else {
                        Loading()
                    }
                } else {
                    if (image != null) {
                        Yes(image!!, "123", 100.0f)
                    } else {
                        Loading()
                    }
                }
            }
        }

        val takePhotoFromCameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { takenPhoto ->
            if (takenPhoto != null) {
                image = takenPhoto
                 viewModel.update()
                viewModel.upload(takenPhoto)
            }
        }

        LaunchedEffect(true) {
            takePhotoFromCameraLauncher.launch()
        }
    }
}

@Composable
fun Loading() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(vertical = 10.dp),
        shape = RoundedCornerShape(15.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Headline1(text = "잠시만 기다려 주세요..", style = SgxTypography.headline1.copy(fontWeight = FontWeight.Bold, textAlign = TextAlign.Center))
            }
        }
    }
}

@Composable
fun No(image: Bitmap) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(vertical = 10.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .size(width = 220.dp, height = 330.dp)
                    ) {
                        Image(bitmap = image.asImageBitmap(), contentDescription = null, modifier = Modifier.fillMaxSize())
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Headline1(text = "인증 사회적 기업이\n아닙니다", textAlign = TextAlign.Center, textColor = SgxTheme.color.Error, style = SgxTypography.headline1.copy(fontWeight = FontWeight.Bold))
            }
            Spacer(
                modifier = Modifier
                    .height(70.dp)
            )
        }
    }
}

@Composable
fun Yes(image: Bitmap, name: String, hoak: Float) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(vertical = 10.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .size(width = 220.dp, height = 330.dp)
                    ) {
                        Image(bitmap = image.asImageBitmap(), contentDescription = null, modifier = Modifier.fillMaxSize())
                    }
                }
                Row(Modifier.fillMaxWidth(), Arrangement.Center) {
                    Title1(text = name)
                }
                Row(Modifier.fillMaxWidth(), Arrangement.Center) {
                    Body1(text = "정확도: ${hoak}%")
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Headline1(text = "인증 사회적 기업\n입니다", textAlign = TextAlign.Center, textColor = Color(0xFF17AA26), style = SgxTypography.headline1.copy(fontWeight = FontWeight.Bold))
            }
            Spacer(
                modifier = Modifier
                    .height(70.dp)
            )
        }
    }
}