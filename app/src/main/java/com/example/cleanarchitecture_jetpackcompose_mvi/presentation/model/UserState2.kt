package com.example.cleanarchitecture_jetpackcompose_mvi.presentation.model

import com.example.cleanarchitecture_jetpackcompose_mvi.domain.model.User

sealed class UserState2 {
    data object Loading : UserState2()
    data class Success(val users: List<User>) : UserState2()
    data class Error(val msg: String) : UserState2()
}