package com.app.project.data.remote.api

import com.app.project.data.remote.dto.AuthRequest
import com.app.project.data.remote.dto.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    suspend fun login(@Body authRequest: AuthRequest): Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body authRequest: AuthRequest): Response<Unit>
}
