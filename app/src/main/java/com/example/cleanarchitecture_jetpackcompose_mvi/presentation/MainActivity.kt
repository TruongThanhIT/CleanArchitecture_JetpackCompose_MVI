package com.example.cleanarchitecture_jetpackcompose_mvi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cleanarchitecture_jetpackcompose_mvi.presentation.ui.theme.CleanArchitecture_JetpackCompose_MVITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CleanArchitecture_JetpackCompose_MVITheme {
                UsersScreenRoot()
            }
        }
    }
}