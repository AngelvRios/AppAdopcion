package com.app.project.domain.usecase

import com.app.project.domain.repository.PetRepository
import javax.inject.Inject

class DeletePetUseCase @Inject constructor(
    private val repository: PetRepository
) {
    suspend operator fun invoke(id: Int): Boolean {
        return repository.deletePet(id)
    }
}
