package com.app.project.data.remote.api

import com.app.project.data.remote.dto.AdoptionRequestDto
import retrofit2.Response
import retrofit2.http.*

interface AdoptionApi {
    @GET("adoptions")
    suspend fun getAdoptions(): Response<List<AdoptionRequestDto>>

    @GET("adoptions/{id}")
    suspend fun getAdoptionById(@Path("id") id: Int): Response<AdoptionRequestDto>

    @POST("adoptions")
    suspend fun createAdoption(@Body adoptionDto: AdoptionRequestDto): Response<Unit>

    @PUT("adoptions/{id}")
    suspend fun updateAdoption(@Path("id") id: Int, @Body adoptionDto: AdoptionRequestDto): Response<Unit>

    @DELETE("adoptions/{id}")
    suspend fun deleteAdoption(@Path("id") id: Int): Response<Unit>
}
