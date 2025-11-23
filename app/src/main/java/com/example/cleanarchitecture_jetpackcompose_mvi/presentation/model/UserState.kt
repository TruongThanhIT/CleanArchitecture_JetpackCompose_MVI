package com.example.cleanarchitecture_jetpackcompose_mvi.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class UserState (
    val users: List<UserUIModel> = listOf(),
    val isLoading: Boolean = false
)