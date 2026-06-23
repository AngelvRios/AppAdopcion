package com.app.project.domain.model

data class AdoptionRequest(
    val id: Int,
    val usuarioId: Int,
    val mascotaId: Int,
    val fecha: String,
    val estado: String
)
