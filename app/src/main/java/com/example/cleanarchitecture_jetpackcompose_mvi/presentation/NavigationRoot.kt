package com.example.cleanarchitecture_jetpackcompose_mvi.presentation

import android.text.Layout
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.cleanarchitecture_jetpackcompose_mvi.domain.model.UserDetail

@Composable
fun NavigationRoot (
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.UserGraph
    ) {
        userGraph(navController)
    }
}

private fun NavGraphBuilder.usersScreen(navController: NavHostController) {
    composable<Routes.UsersScreenRoute> {
        UsersScreenRoot(onClick = { id ->
            navController.navigate(Routes.UserDetailScreenRoute(id))

        })
    }
}

private fun NavGraphBuilder.userDetailScreen() {
    composable<Routes.UserDetailScreenRoute> {
        Box(contentAlignment = Alignment.Center) {
            Text(it.id)
        }
    }
}

private fun NavGraphBuilder.userGraph(navController: NavHostController) {
    navigation<Routes.UserGraph>(
        startDestination = Routes.UsersScreenRoute
    ) {
        usersScreen(navController)
        userDetailScreen()
    }
}