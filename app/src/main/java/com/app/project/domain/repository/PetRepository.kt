package com.app.project.domain.repository

import com.app.project.domain.model.Pet
import kotlinx.coroutines.flow.Flow

interface PetRepository {
    suspend fun getPets(): Flow<List<Pet>>
    suspend fun getPetById(id: Int): Pet?
    suspend fun createPet(pet: Pet): Boolean
    suspend fun updatePet(id: Int, pet: Pet): Boolean
    suspend fun deletePet(id: Int): Boolean
}
