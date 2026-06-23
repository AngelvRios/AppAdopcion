package com.app.project.data.remote.api

import com.app.project.data.remote.dto.PetDto
import retrofit2.Response
import retrofit2.http.*

interface PetApi {
    @GET("pets")
    suspend fun getPets(): Response<List<PetDto>>

    @GET("pets/{id}")
    suspend fun getPetById(@Path("id") id: Int): Response<PetDto>

    @POST("pets")
    suspend fun createPet(@Body petDto: PetDto): Response<Unit>

    @PUT("pets/{id}")
    suspend fun updatePet(@Path("id") id: Int, @Body petDto: PetDto): Response<Unit>

    @DELETE("pets/{id}")
    suspend fun deletePet(@Path("id") id: Int): Response<Unit>
}
