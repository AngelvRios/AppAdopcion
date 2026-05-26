package com.periodization.myapplication.domain.model

import java.io.Serializable

data class Pet(
    val id: Int,
    val name: String,
    val type: String,
    val age: String,
    val gender: String,
    val location: String?,
    val description: String?,
    val imageUrl: String?,
    val foundation: String?
) : Serializable