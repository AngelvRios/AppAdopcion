package com.app.project.data.mapper

import com.app.project.data.remote.dto.PetDto
import com.app.project.domain.model.Pet

fun PetDto.toDomain(): Pet {
    return Pet(
        id = id,
        nombre = nombre,
        especie = especie,
        raza = null, // Backend doesn't have raza, mapping to null
        edad = edad,
        descripcion = descripcion,
        imagenUrl = imagenUrl,
        estado = null // Backend doesn't have estado, mapping to null
    )
}

fun Pet.toDto(): PetDto {
    return PetDto(
        id = if (id <= 0) 0 else id, // Ensure id is 0 for new pets
        nombre = nombre,
        especie = especie,
        edad = edad,
        genero = "Macho", // Default or extract from description
        municipio = "Ciudad", // Default or map from somewhere
        descripcion = descripcion,
        imagenUrl = imagenUrl
    )
}
