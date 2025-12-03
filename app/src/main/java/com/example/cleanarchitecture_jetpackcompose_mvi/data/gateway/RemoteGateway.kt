package com.example.cleanarchitecture_jetpackcompose_mvi.data.gateway

import com.example.cleanarchitecture_jetpackcompose_mvi.domain.model.User

interface RemoteGateway {
    suspend fun fetchUsers(): List<User>
}