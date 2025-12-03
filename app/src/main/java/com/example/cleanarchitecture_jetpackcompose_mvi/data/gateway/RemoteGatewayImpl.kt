package com.example.cleanarchitecture_jetpackcompose_mvi.data.gateway

import com.example.cleanarchitecture_jetpackcompose_mvi.domain.model.User
import javax.inject.Inject

class RemoteGatewayImpl @Inject constructor() : RemoteGateway {
    override suspend fun fetchUsers(): List<User> {
        val users = mutableListOf<User>()
        for (index in 1..10) {
            val user = User(index.toString(), "User $index", index + 19)
            users.add(user)
        }
        return users
    }
}