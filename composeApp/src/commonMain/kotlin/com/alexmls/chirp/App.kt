package com.alexmls.chirp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.alexmls.chirp.navigation.DeepLinkListener
import com.alexmls.chirp.navigation.NavigationRoot
import com.alexmls.core.designsystem.theme.ChirpTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    DeepLinkListener(navController)
    ChirpTheme {
        NavigationRoot(navController)
    }
}