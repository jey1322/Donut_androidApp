package com.codein.donut.data

import android.widget.Toast
import com.codein.donut.data.model.NotasResponse
import com.codein.donut.data.network.UnanService

class UnanRepository {

    private val api = UnanService()

    suspend fun getAllNotes() : NotasResponse{
        val response = api.getNotes()
        if (response.student.id != "") {
            NotasProvider.notas = response
            return response
        } else {
            NotasProvider.notas = null
            throw Exception("Error")
        }
    }
}