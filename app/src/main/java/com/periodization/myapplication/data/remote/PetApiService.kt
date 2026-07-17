package com.periodization.myapplication.data.remote

import com.periodization.myapplication.domain.model.Pet
import retrofit2.http.GET

interface PetApiService {
    @GET("pets")
    suspend fun getPets(): List<Pet>

    companion object {
        const val BASE_URL = "http://10.70.113.70:8080/"
    }
}
