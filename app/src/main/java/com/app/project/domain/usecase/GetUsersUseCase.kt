package com.app.project.domain.usecase

import com.app.project.domain.model.User
import com.app.project.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): Flow<List<User>> {
        return repository.getUsers()
    }
}
