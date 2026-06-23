package com.app.project.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("correo") val correo: String,
    @SerializedName("password") val password: String,
    @SerializedName("telefono") val telefono: String,
    @SerializedName("rol") val rol: String
)
