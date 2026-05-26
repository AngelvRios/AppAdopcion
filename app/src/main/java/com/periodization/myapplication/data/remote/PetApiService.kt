package com.periodization.myapplication.data.remote

import com.periodization.myapplication.data.remote.dto.PetResponse
import retrofit2.http.GET

interface PetApiService {
    @GET("animales")
    suspend fun getPets(): PetResponse

    companion object {
        const val BASE_URL = "https://huachitos.cl/api/"
    }
}