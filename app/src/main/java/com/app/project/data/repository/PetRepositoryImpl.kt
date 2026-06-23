package com.app.project.data.repository

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
        val response = remoteDataSource.getPets()
        if (response.isSuccessful) {
            emit(response.body()?.map { it.toDomain() } ?: emptyList())
        }
    }

    override suspend fun getPetById(id: Int): Pet? {
        val response = remoteDataSource.getPetById(id)
        return if (response.isSuccessful) response.body()?.toDomain() else null
    }

    override suspend fun createPet(pet: Pet): Boolean {
        val response = remoteDataSource.createPet(pet.toDto())
        return response.isSuccessful
    }

    override suspend fun updatePet(id: Int, pet: Pet): Boolean {
        val response = remoteDataSource.updatePet(id, pet.toDto())
        return response.isSuccessful
    }

    override suspend fun deletePet(id: Int): Boolean {
        val response = remoteDataSource.deletePet(id)
        return response.isSuccessful
    }
}
