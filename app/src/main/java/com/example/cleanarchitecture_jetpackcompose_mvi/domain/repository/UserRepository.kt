package com.example.cleanarchitecture_jetpackcompose_mvi.domain.repository

import com.example.cleanarchitecture_jetpackcompose_mvi.domain.model.User
import com.example.cleanarchitecture_jetpackcompose_mvi.domain.model.UserDetail

interface UserRepository {
    suspend fun fetchUsers(): List<User>
    suspend fun getUserDetail(id: String): UserDetail
}