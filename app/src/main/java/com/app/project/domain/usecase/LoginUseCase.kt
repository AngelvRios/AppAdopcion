package com.app.project.domain.usecase

import com.app.project.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): String? {
        return repository.login(email, password)
    }
}
