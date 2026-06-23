package com.app.project.domain.usecase

import com.app.project.domain.model.User
import com.app.project.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(user: User): Boolean {
        return repository.register(user)
    }
}
