package com.app.project.domain.repository

import com.app.project.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUsers(): Flow<List<User>>
    suspend fun getUserById(id: Int): User?
    suspend fun createUser(user: User): Boolean
    suspend fun updateUser(id: Int, user: User): Boolean
    suspend fun deleteUser(id: Int): Boolean
}
