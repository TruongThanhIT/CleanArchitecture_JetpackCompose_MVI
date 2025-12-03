package com.example.cleanarchitecture_jetpackcompose_mvi.data.repository

import com.example.cleanarchitecture_jetpackcompose_mvi.data.gateway.RemoteGateway
import com.example.cleanarchitecture_jetpackcompose_mvi.domain.model.User
import com.example.cleanarchitecture_jetpackcompose_mvi.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val remoteGateway: RemoteGateway) : UserRepository {
    override suspend fun fetchUsers(): List<User> {
        return remoteGateway.fetchUsers()
    }
}