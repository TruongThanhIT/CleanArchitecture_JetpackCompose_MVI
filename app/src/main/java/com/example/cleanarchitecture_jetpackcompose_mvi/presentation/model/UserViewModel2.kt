package com.example.cleanarchitecture_jetpackcompose_mvi.presentation.model

import UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture_jetpackcompose_mvi.domain.usecase.GetUsersUseCase
import com.example.cleanarchitecture_jetpackcompose_mvi.presentation.mapper.toUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel2 @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(UserState())
    val uiState = _uiState.asStateFlow()

    private val _event = Channel<UserEvent>()
    val even = _event.receiveAsFlow()

    init {
        loadData()
    }

    fun loadData() {
        val job = SupervisorJob()
        val scrope = CoroutineScope(job + Dispatchers.IO)

        viewModelScope.launch {
            val result1Def = async { getUsersUseCase.invoke() }
            val result2Def = async { getUsersUseCase.invoke() }
            val result = result1Def.await() + result2Def.await()
        }
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(isLoading = true)
                }
                val users = getUsersUseCase.invoke()
                _uiState.update {
                    it.copy(users = users.map { it.toUIModel() }, isLoading = false)
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false)
                }
                _event.send(UserEvent.Error("Unknow error"))
            }
        }
    }

}