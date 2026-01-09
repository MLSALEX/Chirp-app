package com.alexmls.chirp

import androidx.compose.runtime.Composable
import com.alexmls.auth.presentation.register.RegisterRoot
import com.alexmls.core.designsystem.theme.ChirpTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    ChirpTheme {
        RegisterRoot()
    }
}