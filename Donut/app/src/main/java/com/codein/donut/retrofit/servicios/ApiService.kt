package com.codein.donut.retrofit.servicios

import com.codein.donut.retrofit.LoginRequest
import com.codein.donut.retrofit.LoginResponse
import com.codein.donut.retrofit.Urls
import retrofit2.Call
import retrofit2.http.POST

interface ApiService {

    @POST(Urls.NOTAS_URL)
    fun fetchNotas(request: LoginRequest) : Call<LoginResponse>
}