package com.app.project.data.remote.api

import com.app.project.data.remote.dto.LoginRequest
import com.app.project.data.remote.dto.LoginResponse
import com.app.project.data.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("auth/register")
    suspend fun register(@Body userDto: UserDto): Response<Unit>
}
