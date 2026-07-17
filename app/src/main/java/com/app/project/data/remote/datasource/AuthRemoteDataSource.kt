package com.app.project.data.remote.datasource

import com.app.project.data.remote.api.AuthApi
import com.app.project.data.remote.dto.AuthRequest
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val api: AuthApi
) {
    suspend fun login(authRequest: AuthRequest) = api.login(authRequest)
    suspend fun register(authRequest: AuthRequest) = api.register(authRequest)
}
