package com.example.cleanarchitecture_jetpackcompose_mvi.domain.usecase

import android.widget.Toast
import com.example.cleanarchitecture_jetpackcompose_mvi.domain.model.User
import com.example.cleanarchitecture_jetpackcompose_mvi.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() : List<User> {
        return userRepository.fetchUsers()
    }
}