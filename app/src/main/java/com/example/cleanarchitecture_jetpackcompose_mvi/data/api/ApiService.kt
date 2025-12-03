package com.example.cleanarchitecture_jetpackcompose_mvi.data.api

import com.example.cleanarchitecture_jetpackcompose_mvi.domain.model.User

interface ApiService {
    suspend fun fetchUsers(): List<User>
}