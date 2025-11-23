package com.example.cleanarchitecture_jetpackcompose_mvi.domain.repository

import com.example.cleanarchitecture_jetpackcompose_mvi.domain.model.User

interface UserRepository {
    suspend fun fetchUsers(): List<User>
}