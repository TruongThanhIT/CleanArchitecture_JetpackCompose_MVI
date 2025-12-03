package com.example.cleanarchitecture_jetpackcompose_mvi.domain.usecase

import com.example.cleanarchitecture_jetpackcompose_mvi.domain.model.UserDetail
import com.example.cleanarchitecture_jetpackcompose_mvi.domain.repository.UserRepository
import jakarta.inject.Inject

class GetUserDetailUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend fun invoke(id: String): UserDetail {
        return userRepository.getUserDetail(id)
    }
}