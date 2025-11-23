package com.example.cleanarchitecture_jetpackcompose_mvi.presentation.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture_jetpackcompose_mvi.domain.usecase.GetUsersUseCase
import com.example.cleanarchitecture_jetpackcompose_mvi.presentation.mapper.toUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersScreenViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(UserState())
    val uiState = _uiState.asStateFlow()

    private val _eventChannel = Channel<UserEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(isLoading = true)
                }

                val users = getUsersUseCase()
                _uiState.update {
                    it.copy(users = users.map { it.toUIModel() }, isLoading = false)
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false)
                }
                _eventChannel.send(UserEvent.Error("Failed to load users"))
            }
        }
    }
}