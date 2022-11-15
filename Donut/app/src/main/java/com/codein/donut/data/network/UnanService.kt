package com.codein.donut.data.network

import com.codein.donut.core.RetrofitHelper
import com.codein.donut.data.model.LoginRequest
import com.codein.donut.data.model.NotasResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UnanService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getNotes(): NotasResponse {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(UnanApiClient::class.java)
                .getAllNotes(LoginRequest(id = "16-01447-0", password = "NAFUWP", year = 2022))
            response.body()!!
        }
    }
}