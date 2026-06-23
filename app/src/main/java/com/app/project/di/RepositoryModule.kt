package com.app.project.di

import com.app.project.data.repository.AuthRepositoryImpl
import com.app.project.data.repository.PetRepositoryImpl
import com.app.project.domain.repository.AuthRepository
import com.app.project.domain.repository.PetRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindPetRepository(
        petRepositoryImpl: PetRepositoryImpl
    ): PetRepository
}
