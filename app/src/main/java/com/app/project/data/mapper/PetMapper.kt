package com.app.project.data.mapper

import com.app.project.data.remote.dto.PetDto
import com.app.project.domain.model.Pet

fun PetDto.toDomain(): Pet {
    return Pet(
        id = id,
        nombre = nombre,
        especie = especie,
        raza = raza,
        edad = edad,
        descripcion = descripcion,
        imagenUrl = imagenUrl,
        estado = estado
    )
}

fun Pet.toDto(): PetDto {
    return PetDto(
        id = id,
        nombre = nombre,
        especie = especie,
        raza = raza,
        edad = edad,
        descripcion = descripcion,
        imagenUrl = imagenUrl,
        estado = estado
    )
}
