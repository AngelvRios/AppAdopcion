package com.app.project.data.mapper

import com.app.project.data.remote.dto.UserDto
import com.app.project.domain.model.User

fun UserDto.toDomain(): User {
    return User(
        id = id,
        nombre = nombre,
        correo = correo,
        password = password,
        telefono = telefono,
        rol = rol
    )
}

fun User.toDto(): UserDto {
    return UserDto(
        id = id,
        nombre = nombre,
        correo = correo,
        password = password,
        telefono = telefono,
        rol = rol
    )
}
