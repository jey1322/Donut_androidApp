package com.codein.donut.domain

import com.codein.donut.data.UnanRepository
import com.codein.donut.data.model.NotasResponse

class GetNotasUseCase {

    private val repository = UnanRepository()

    suspend operator fun invoke(): NotasResponse? = repository.getAllNotes()
}