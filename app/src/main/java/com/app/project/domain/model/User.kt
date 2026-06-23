package com.app.project.domain.model

data class User(
    val id: Int,
    val nombre: String,
    val correo: String,
    val password: String,
    val telefono: String,
    val rol: String
)
