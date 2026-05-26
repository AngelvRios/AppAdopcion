package com.periodization.myapplication

import java.io.Serializable

data class Mascota(
    val nombre: String,
    val edad: String,
    val ubicacion: String,
    val descripcion: String,
    val fundacion: String,
    val imageUrl: String? = null
) : Serializable