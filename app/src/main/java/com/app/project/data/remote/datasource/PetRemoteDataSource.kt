package com.app.project.data.remote.datasource

import com.app.project.data.remote.api.PetApi
import com.app.project.data.remote.dto.PetDto
import javax.inject.Inject

class PetRemoteDataSource @Inject constructor(
    private val api: PetApi
) {
    suspend fun getPets() = api.getPets()
    suspend fun getPetById(id: Int) = api.getPetById(id)
    suspend fun createPet(petDto: PetDto) = api.createPet(petDto)
    suspend fun updatePet(id: Int, petDto: PetDto) = api.updatePet(id, petDto)
    suspend fun deletePet(id: Int) = api.deletePet(id)
}
