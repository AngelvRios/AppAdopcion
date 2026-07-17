package com.app.project.data.repository

import android.util.Log
import com.app.project.data.mapper.toDomain
import com.app.project.data.mapper.toDto
import com.app.project.data.remote.datasource.PetRemoteDataSource
import com.app.project.domain.model.Pet
import com.app.project.domain.repository.PetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PetRepositoryImpl @Inject constructor(
    private val remoteDataSource: PetRemoteDataSource
) : PetRepository {
    override suspend fun getPets(): Flow<List<Pet>> = flow {
        try {
            val response = remoteDataSource.getPets()
            if (response.isSuccessful) {
                emit(response.body()?.map { it.toDomain() } ?: emptyList())
            } else {
                Log.e("PetRepository", "Error getting pets: ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("PetRepository", "Exception getting pets", e)
        }
    }

    override suspend fun getPetById(id: Int): Pet? {
        return try {
            val response = remoteDataSource.getPetById(id)
            if (response.isSuccessful) response.body()?.toDomain() else null
        } catch (e: Exception) {
            Log.e("PetRepository", "Exception getting pet by id", e)
            null
        }
    }

    override suspend fun createPet(pet: Pet): Boolean {
        return try {
            val response = remoteDataSource.createPet(pet.toDto())
            if (!response.isSuccessful) {
                Log.e("PetRepository", "Error creating pet: ${response.code()} ${response.errorBody()?.string()}")
            }
            response.isSuccessful
        } catch (e: Exception) {
            Log.e("PetRepository", "Exception creating pet", e)
            false
        }
    }

    override suspend fun updatePet(id: Int, pet: Pet): Boolean {
        return try {
            val response = remoteDataSource.updatePet(id, pet.toDto())
            if (!response.isSuccessful) {
                Log.e("PetRepository", "Error updating pet: ${response.code()} ${response.errorBody()?.string()}")
            }
            response.isSuccessful
        } catch (e: Exception) {
            Log.e("PetRepository", "Exception updating pet", e)
            false
        }
    }

    override suspend fun deletePet(id: Int): Boolean {
        return try {
            val response = remoteDataSource.deletePet(id)
            if (!response.isSuccessful) {
                Log.e("PetRepository", "Error deleting pet: ${response.code()} ${response.errorBody()?.string()}")
            }
            response.isSuccessful
        } catch (e: Exception) {
            Log.e("PetRepository", "Exception deleting pet", e)
            false
        }
    }
}
