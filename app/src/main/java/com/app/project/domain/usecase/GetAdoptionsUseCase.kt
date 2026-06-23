package com.app.project.domain.usecase

import com.app.project.domain.model.AdoptionRequest
import com.app.project.domain.repository.AdoptionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAdoptionsUseCase @Inject constructor(
    private val repository: AdoptionRepository
) {
    suspend operator fun invoke(): Flow<List<AdoptionRequest>> {
        return repository.getAdoptions()
    }
}
