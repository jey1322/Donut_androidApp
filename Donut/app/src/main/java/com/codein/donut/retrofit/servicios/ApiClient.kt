package com.codein.donut.retrofit.servicios

import android.content.Context
import com.codein.donut.retrofit.Urls
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private lateinit var apiService: ApiService

    fun getApiService(context: Context):ApiService{
        if(!::apiService.isInitialized)
        {
            val retrofit = Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            apiService = retrofit.create(ApiService::class.java)
        }
        return apiService
    }
}