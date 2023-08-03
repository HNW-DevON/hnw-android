package com.bestswlkh0310.sui.ui.com

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bestswlkh0310.sui.data.RetrofitClient
import com.bestswlkh0310.sui.data.RetrofitService
import com.bestswlkh0310.sui.ui.join.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream

data class CamState(
    val name: String = "",
    val hoak: Float = 0f,
    val no: Boolean = false,
    val status: CamStatus = CamStatus.Loading
)

sealed class CamStatus {
    object Loading: CamStatus()
    object Complete: CamStatus()
}

class ComViewModel: ViewModel() {
    private val _state = MutableStateFlow(CamState())
    val state = _state.asStateFlow()

    private fun createRequestBodyFromBitmap(bitmap: Bitmap): RequestBody {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        return RequestBody.create("image/*".toMediaTypeOrNull(), byteArrayOutputStream.toByteArray())
    }

    private fun prepareImagePart(bitmap: Bitmap, partName: String): MultipartBody.Part {
        val requestBody = createRequestBodyFromBitmap(bitmap)
        return MultipartBody.Part.createFormData(partName, "image.jpg", requestBody)
    }
    fun upload(bitmap: Bitmap) {
        val imagePart: MultipartBody.Part = prepareImagePart(bitmap, "image")

        viewModelScope.launch {
            val response = RetrofitClient.retrofit.uploadImage(imagePart)
            _state.value.copy(status = CamStatus.Complete)
            // todo:  state처리
        }

    }

    fun update() {
        _state.value = _state.value.copy(status = CamStatus.Complete)
    }
}