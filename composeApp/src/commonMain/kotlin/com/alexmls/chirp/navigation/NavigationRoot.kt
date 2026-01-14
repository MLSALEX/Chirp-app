package com.alexmls.chirp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alexmls.auth.presentation.chat_list.ChatListRoute
import com.alexmls.auth.presentation.chat_list.ChatListScreenRoot
import com.alexmls.auth.presentation.navigation.AuthGraphRoutes
import com.alexmls.auth.presentation.navigation.authGraph

@Composable
fun NavigationRoot(
    navController: NavHostController,
    startDestination: Any
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        authGraph(
            navController = navController,
            onLoginSuccess = {
                navController.navigate(ChatListRoute) {
                    popUpTo(AuthGraphRoutes.Graph) {
                        inclusive = true
                    }
                }
            }
        )

        composable<ChatListRoute> {
            ChatListScreenRoot()
        }
    }
}