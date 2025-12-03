package com.example.cleanarchitecture_jetpackcompose_mvi.presentation.model

sealed class UserEvent2 {
    data class Error(val msg: String) : UserState2()
}