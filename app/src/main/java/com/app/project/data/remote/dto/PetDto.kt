package com.app.project.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PetDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val nombre: String,
    @SerializedName("type") val especie: String?,
    @SerializedName("age") val edad: String?,
    @SerializedName("gender") val genero: String?,
    @SerializedName("location") val municipio: String?,
    @SerializedName("description") val descripcion: String?,
    @SerializedName("imageUrl") val imagenUrl: String?
)
