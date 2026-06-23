package com.app.project.domain.usecase

import com.app.project.domain.model.Pet
import com.app.project.domain.repository.PetRepository
import javax.inject.Inject

class UpdatePetUseCase @Inject constructor(
    private val repository: PetRepository
) {
    suspend operator fun invoke(id: Int, pet: Pet): Boolean {
        return repository.updatePet(id, pet)
    }
}
