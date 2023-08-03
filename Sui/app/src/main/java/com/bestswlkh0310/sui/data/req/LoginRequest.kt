package com.bestswlkh0310.sui.data.req

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("email") val id: String,
    @SerializedName("password") val pw: String
)