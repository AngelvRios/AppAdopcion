package com.app.project.domain.repository

import com.app.project.domain.model.AdoptionRequest
import kotlinx.coroutines.flow.Flow

interface AdoptionRepository {
    suspend fun getAdoptions(): Flow<List<AdoptionRequest>>
    suspend fun createAdoption(adoptionRequest: AdoptionRequest): Boolean
    suspend fun updateAdoption(id: Int, adoptionRequest: AdoptionRequest): Boolean
    suspend fun deleteAdoption(id: Int): Boolean
}
