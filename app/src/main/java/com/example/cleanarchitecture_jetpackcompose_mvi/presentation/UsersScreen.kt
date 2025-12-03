package com.example.cleanarchitecture_jetpackcompose_mvi.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cleanarchitecture_jetpackcompose_mvi.presentation.model.UserEvent
import com.example.cleanarchitecture_jetpackcompose_mvi.presentation.model.UserState
import com.example.cleanarchitecture_jetpackcompose_mvi.presentation.model.UserUIModel
import com.example.cleanarchitecture_jetpackcompose_mvi.presentation.model.UsersScreenViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun UsersScreenRoot(
    viewModel: UsersScreenViewModel = hiltViewModel(),
    onClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.eventChannel.collectLatest { event ->
            when (event) {
                is UserEvent.Error -> {
                    Toast.makeText(context, event.error, Toast.LENGTH_SHORT).show()
                }

                is UserEvent.Success -> {
                    Toast.makeText(context, "Load users success", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    UsersScreen(uiState = uiState, onClick = onClick)
}

@Composable
private fun UsersScreen(
    uiState: UserState,
    onClick: (String) -> Unit
) {
    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                modifier = Modifier.semantics {
                    contentDescription = "Progress Indicator"
                }
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(uiState.users) { user ->
                UserItem(user, onClick = onClick)
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun UserItem(
    user: UserUIModel,
    onClick: (String) -> Unit
) {
    Card(onClick = {
        onClick.invoke(user.id)
    }) {
        Column(
            modifier = Modifier
                .background(color = Color.Magenta)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(user.name, color = Color.White)
            Text("${user.age}", color = Color.White)
        }
    }
}

@Preview
@Composable
private fun UsersScreenPreview() {
    MaterialTheme {
        UsersScreen(
            uiState = UserState(
                users = listOf(
                    UserUIModel(
                        id = "1",
                        name = "Pig Joe",
                        age = 50
                    )
                )
            ),
            onClick = {}
        )
    }
}