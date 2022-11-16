package com.codein.donut.retrofit.servicios

import com.codein.donut.retrofit.LoginRequest
import com.codein.donut.retrofit.LoginResponse
import com.codein.donut.retrofit.Urls
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @POST(Urls.NOTAS_URL)
    @Headers("Accept: application/json")
    fun login( @Body request: LoginRequest): Call<LoginResponse>
}