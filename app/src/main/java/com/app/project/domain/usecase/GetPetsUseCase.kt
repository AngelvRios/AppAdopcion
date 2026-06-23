package com.app.project.domain.usecase

import com.app.project.domain.model.Pet
import com.app.project.domain.repository.PetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPetsUseCase @Inject constructor(
    private val repository: PetRepository
) {
    suspend operator fun invoke(): Flow<List<Pet>> {
        return repository.getPets()
    }
}
