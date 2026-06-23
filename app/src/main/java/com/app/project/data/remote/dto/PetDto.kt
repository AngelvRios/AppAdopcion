package com.app.project.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PetDto(
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("especie") val especie: String,
    @SerializedName("raza") val raza: String,
    @SerializedName("edad") val edad: Int,
    @SerializedName("descripcion") val descripcion: String,
    @SerializedName("imagen_url") val imagenUrl: String,
    @SerializedName("estado") val estado: String
)
