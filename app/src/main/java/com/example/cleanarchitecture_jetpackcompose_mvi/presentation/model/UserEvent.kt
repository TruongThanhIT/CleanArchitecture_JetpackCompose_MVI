package com.example.cleanarchitecture_jetpackcompose_mvi.presentation.model

sealed interface UserEvent {
    data class Error(val error: String) : UserEvent
    data object Success : UserEvent
}