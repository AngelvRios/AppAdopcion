package com.periodization.myapplication.data.repository

import com.periodization.myapplication.data.remote.PetApiService
import com.periodization.myapplication.domain.model.Pet
import com.periodization.myapplication.domain.repository.PetRepository
import javax.inject.Inject

class PetRepositoryImpl @Inject constructor(
    private val api: PetApiService
) : PetRepository {
    override suspend fun getPets(): Result<List<Pet>> {
        return try {
            val pets = api.getPets()
            Result.success(pets)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
