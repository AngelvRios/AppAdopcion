package com.periodization.myapplication.data.mapper

import com.periodization.myapplication.data.remote.dto.PetDto
import com.periodization.myapplication.domain.model.Pet

fun PetDto.toDomain(): Pet {
    return Pet(
        id = id,
        name = nombre,
        type = tipo,
        age = edad,
        gender = genero,
        location = municipio,
        description = descripcion,
        imageUrl = imagen,
        foundation = equipo
    )
}