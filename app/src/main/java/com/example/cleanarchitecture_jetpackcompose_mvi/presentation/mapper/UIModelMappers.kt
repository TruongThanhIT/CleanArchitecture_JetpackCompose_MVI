package com.example.cleanarchitecture_jetpackcompose_mvi.presentation.mapper

import com.example.cleanarchitecture_jetpackcompose_mvi.domain.model.User
import com.example.cleanarchitecture_jetpackcompose_mvi.presentation.model.UserUIModel

fun User.toUIModel(): UserUIModel {
    return UserUIModel(id = id, name = name, age = age)
}