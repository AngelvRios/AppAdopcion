package com.app.project.domain.model

data class Pet(
    val id: Int,
    val nombre: String,
    val especie: String,
    val raza: String,
    val edad: Int,
    val descripcion: String,
    val imagenUrl: String,
    val estado: String
)
