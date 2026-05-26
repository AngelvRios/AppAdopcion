package com.periodization.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MascotaApiService {
    @GET("mascotas")
    suspend fun getMascotas(): List<Mascota>
}

object RetrofitClient {
    private const val BASE_URL = "https://my-json-server.typicode.com/icede/demo/" // Cambia esto por tu repo real o una URL válida

    val instance: MascotaApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(MascotaApiService::class.java)
    }
}