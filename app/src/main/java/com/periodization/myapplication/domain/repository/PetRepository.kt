package com.periodization.myapplication.domain.repository

import com.periodization.myapplication.domain.model.Pet

interface PetRepository {
    suspend fun getPets(): Result<List<Pet>>
}