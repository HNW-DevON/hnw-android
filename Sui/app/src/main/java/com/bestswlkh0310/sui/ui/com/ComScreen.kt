package com.bestswlkh0310.sui.ui.com

import android.graphics.Bitmap
import android.util.Base64
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bestswlkh0310.sui.ui.util.Application
import java.io.ByteArrayOutputStream

@Composable
fun ComScreen(
    viewModel: ComViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val a = Application.prefs
        val context = LocalContext.current

        val takePhotoFromCameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { takenPhoto ->
            if (takenPhoto != null) {
                viewModel.upload(takenPhoto)
            /*
                val baos = ByteArrayOutputStream()
                takenPhoto.compress(Bitmap.CompressFormat.PNG, 100, baos)
                val b: ByteArray = baos.toByteArray()
                val encoded: String = Base64.encodeToString(b, Base64.DEFAULT)
                a.a = encoded
*/

            } else {

            }
        }

        LaunchedEffect(true) {
            takePhotoFromCameraLauncher.launch()
        }
    }
}