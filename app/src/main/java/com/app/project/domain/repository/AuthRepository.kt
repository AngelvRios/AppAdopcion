package com.app.project.domain.repository

import com.app.project.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): String?
    suspend fun register(user: User): Boolean
    suspend fun logout()
    fun getToken(): Flow<String?>
}
