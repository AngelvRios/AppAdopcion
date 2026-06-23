package com.app.project.data.remote.datasource

import com.app.project.data.remote.api.AuthApi
import com.app.project.data.remote.dto.LoginRequest
import com.app.project.data.remote.dto.UserDto
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val api: AuthApi
) {
    suspend fun login(loginRequest: LoginRequest) = api.login(loginRequest)
    suspend fun register(userDto: UserDto) = api.register(userDto)
}
