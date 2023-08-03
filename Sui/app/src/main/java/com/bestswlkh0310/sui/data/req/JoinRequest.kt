package com.bestswlkh0310.sui.data.req

import com.google.gson.annotations.SerializedName

data class JoinRequest(
    @SerializedName("email") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("password") val pw: String
)
