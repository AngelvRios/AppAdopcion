package com.app.project.domain.usecase

import com.app.project.domain.model.Pet
import com.app.project.domain.repository.PetRepository
import javax.inject.Inject

class CreatePetUseCase @Inject constructor(
    private val repository: PetRepository
) {
    suspend operator fun invoke(pet: Pet): Boolean {
        return repository.createPet(pet)
    }
}
