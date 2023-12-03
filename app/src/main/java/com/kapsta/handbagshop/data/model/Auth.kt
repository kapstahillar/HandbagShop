package com.kapsta.handbagshop.data.model

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    val userCode: String,
    val userName: String,
    val password: String
)

data class AuthResponse(
    @SerializedName("records")
    val records: List<AuthRecord>
)

data class AuthRecord(
    @SerializedName("sessionKey")
    val sessionKey: String
)