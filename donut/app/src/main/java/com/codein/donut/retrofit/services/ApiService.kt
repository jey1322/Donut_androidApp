package com.codein.donut.retrofit.services

import com.codein.donut.retrofit.LoginRequest
import com.codein.donut.retrofit.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @POST(Urls.LOGIN_URL)
    @Headers("Accept: application/json")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}