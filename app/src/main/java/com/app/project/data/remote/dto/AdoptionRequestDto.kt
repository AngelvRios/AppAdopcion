package com.app.project.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AdoptionRequestDto(
    @SerializedName("id") val id: Int,
    @SerializedName("usuario_id") val usuarioId: Int,
    @SerializedName("mascota_id") val mascotaId: Int,
    @SerializedName("fecha") val fecha: String,
    @SerializedName("estado") val estado: String
)
