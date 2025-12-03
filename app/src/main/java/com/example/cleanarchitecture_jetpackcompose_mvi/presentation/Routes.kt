package com.example.cleanarchitecture_jetpackcompose_mvi.presentation

import kotlinx.serialization.Serializable

sealed interface Routes {
    @Serializable
    data object UsersScreenRoute: Routes
    @Serializable
    data class UserDetailScreenRoute(val id: String): Routes
    @Serializable
    data object UserGraph: Routes
}