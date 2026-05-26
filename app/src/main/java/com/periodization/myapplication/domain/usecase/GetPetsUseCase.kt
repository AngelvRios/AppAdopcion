package com.periodization.myapplication.domain.usecase

import com.periodization.myapplication.domain.model.Pet
import com.periodization.myapplication.domain.repository.PetRepository
import javax.inject.Inject

class GetPetsUseCase @Inject constructor(
    private val repository: PetRepository
) {
    suspend operator fun invoke(): Result<List<Pet>> {
        return repository.getPets()
    }
}