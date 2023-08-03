package com.bestswlkh0310.sui.data

import android.util.Log
import com.bestswlkh0310.sui.data.req.JoinRequest
import com.bestswlkh0310.sui.data.req.LoginRequest

object AuthRepository {
    private val server = RetrofitClient.retrofit
    suspend fun login(loginRequest: LoginRequest) {
        server.login(loginRequest)
    }

}