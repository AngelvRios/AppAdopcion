package com.app.project.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("correo") val correo: String,
    @SerializedName("password") val password: String,
    @SerializedName("nombre") val nombre: String? = null,
    @SerializedName("telefono") val telefono: String? = null
)
