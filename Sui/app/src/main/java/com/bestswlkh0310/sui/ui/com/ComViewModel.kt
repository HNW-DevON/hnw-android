package com.bestswlkh0310.sui.ui.com

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bestswlkh0310.sui.data.RetrofitClient
import com.bestswlkh0310.sui.data.RetrofitService
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream

class ComViewModel: ViewModel() {
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
//        val descriptionPart = "Image description".asRequestBody("text/plain".toMediaTypeOrNull())

        viewModelScope.launch {
            val response = RetrofitClient.retrofit.uploadImage(imagePart)
//            if (response.isSuccessful) {
                // Image uploaded successfully
//            imageUploaded = true
//            } else {
                // Handle error response
//            }
        }

    }
    /*private fun uploadChat() {
        val requestFile: RequestBody = imageFile.asRequestBody(MediaType.parse("multipart/form-data"))
        val body: MultipartBody.Part = MultipartBody.Part.createFormData("uploaded_file", imageFileName, requestFile)
        val retrofitInterface: RetrofitInterface = ApiClient.getApiClient().create(RetrofitInterface::class.java)
        val call: Call<String> = retrofitInterface.request(body)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.e("uploadChat()", "성공 : ")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("uploadChat()", "에러 : " + t.message)
            }
        })
    }*/
}