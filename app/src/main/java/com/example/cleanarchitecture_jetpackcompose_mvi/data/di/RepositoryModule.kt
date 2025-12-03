package com.example.cleanarchitecture_jetpackcompose_mvi.data.di

import com.example.cleanarchitecture_jetpackcompose_mvi.data.repository.UserRepositoryImpl
import com.example.cleanarchitecture_jetpackcompose_mvi.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}