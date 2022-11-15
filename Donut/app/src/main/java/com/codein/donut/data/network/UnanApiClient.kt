package com.codein.donut.data.network

import com.codein.donut.core.Constants
import com.codein.donut.data.model.LoginRequest
import com.codein.donut.data.model.NotasResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UnanApiClient {
    @POST(Constants.NOTES_URL)
    suspend fun getAllNotes(@Body request: LoginRequest): Response<NotasResponse>
}