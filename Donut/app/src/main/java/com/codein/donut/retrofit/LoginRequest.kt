package com.codein.donut.retrofit

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("id")
    val id: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("year")
    val year: Int
)
