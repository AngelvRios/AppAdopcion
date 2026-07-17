package com.app.project.data.repository

import android.util.Log
import com.app.project.data.remote.datasource.AuthRemoteDataSource
import com.app.project.data.remote.dto.AuthRequest
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
        return try {
            val response = remoteDataSource.login(AuthRequest(correo = email, password = password))
            if (response.isSuccessful) {
                val token = response.body()?.token
                token?.let { tokenManager.saveToken(it) }
                token
            } else {
                Log.e("AuthRepository", "Login failed: ${response.code()} ${response.message()}")
                null
            }
        } catch (e: Exception) {
            Log.e("AuthRepository", "Error during login", e)
            null
        }
    }

    override suspend fun register(user: User): Boolean {
        return try {
            val authRequest = AuthRequest(
                nombre = user.nombre,
                correo = user.correo,
                password = user.password,
                telefono = user.telefono
            )
            val response = remoteDataSource.register(authRequest)
            if (response.isSuccessful) {
                true
            } else {
                Log.e("AuthRepository", "Registration failed: ${response.code()} ${response.message()}")
                val errorBody = response.errorBody()?.string()
                Log.e("AuthRepository", "Error body: $errorBody")
                false
            }
        } catch (e: Exception) {
            Log.e("AuthRepository", "Error during registration", e)
            false
        }
    }

    override suspend fun logout() {
        tokenManager.deleteToken()
    }

    override fun getToken(): Flow<String?> {
        return tokenManager.getToken()
    }
}
