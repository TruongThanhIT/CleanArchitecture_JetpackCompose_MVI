package com.example.cleanarchitecture_jetpackcompose_mvi.data.di

import com.example.cleanarchitecture_jetpackcompose_mvi.domain.model.User
import com.example.cleanarchitecture_jetpackcompose_mvi.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {
    override suspend fun fetchUsers(): List<User> {
        val users = mutableListOf<User>()
        for(index in 1..10) {
            val user = User(index.toString(), "User $index", index + 19)
            users.add(user)
        }
        return users
    }
}