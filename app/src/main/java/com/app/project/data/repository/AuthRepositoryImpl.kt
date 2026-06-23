package com.app.project.data.repository

import com.app.project.data.mapper.toDto
import com.app.project.data.remote.datasource.AuthRemoteDataSource
import com.app.project.data.remote.dto.LoginRequest
import com.app.project.domain.model.User
import com.app.project.domain.repository.AuthRepository
import com.app.project.utils.TokenManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource,
    private val tokenManager: TokenManager
) : AuthRepository {
    override suspend fun login(email: String, password: String): String? {
        val response = remoteDataSource.login(LoginRequest(email, password))
        return if (response.isSuccessful) {
            val token = response.body()?.token
            token?.let { tokenManager.saveToken(it) }
            token
        } else null
    }

    override suspend fun register(user: User): Boolean {
        val response = remoteDataSource.register(user.toDto())
        return response.isSuccessful
    }

    override suspend fun logout() {
        tokenManager.deleteToken()
    }

    override fun getToken(): Flow<String?> {
        return tokenManager.getToken()
    }
}
