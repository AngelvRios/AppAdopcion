package com.periodization.myapplication.data.repository

import com.periodization.myapplication.data.mapper.toDomain
import com.periodization.myapplication.data.remote.PetApiService
import com.periodization.myapplication.domain.model.Pet
import com.periodization.myapplication.domain.repository.PetRepository
import javax.inject.Inject

class PetRepositoryImpl @Inject constructor(
    private val api: PetApiService
) : PetRepository {
    override suspend fun getPets(): Result<List<Pet>> {
        return try {
            val response = api.getPets()
            Result.success(response.data.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}