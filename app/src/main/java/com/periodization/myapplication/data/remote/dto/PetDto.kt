package com.periodization.myapplication.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PetResponse(
    val data: List<PetDto>
)

data class PetDto(
    val id: Int,
    val nombre: String,
    val tipo: String,
    val edad: String,
    val genero: String,
    @SerializedName("desc_personalidad") val descripcion: String,
    val imagen: String,
    val municipio: String,
    val equipo: String
)