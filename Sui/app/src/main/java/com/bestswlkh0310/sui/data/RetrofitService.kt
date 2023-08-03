package com.bestswlkh0310.sui.data

import com.bestswlkh0310.sui.data.req.ComResponse
import com.bestswlkh0310.sui.data.req.JoinRequest
import com.bestswlkh0310.sui.data.req.LoginRequest
import com.bestswlkh0310.sui.data.res.LoginResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface RetrofitService {

    @POST("/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @POST("/join")
    suspend fun join(
        @Body joinRequest: JoinRequest
    ): Unit

    @Multipart
    @POST("/search")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part,
    ): List<ComResponse>
}