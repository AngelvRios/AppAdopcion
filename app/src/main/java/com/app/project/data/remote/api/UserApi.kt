package com.app.project.data.remote.api

import com.app.project.data.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.*

interface UserApi {
    @GET("users")
    suspend fun getUsers(): Response<List<UserDto>>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): Response<UserDto>

    @POST("users")
    suspend fun createUser(@Body userDto: UserDto): Response<Unit>

    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body userDto: UserDto): Response<Unit>

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<Unit>
}
