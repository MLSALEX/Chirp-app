package com.alexmls.core.designsystem.preview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.alexmls.core.designsystem.components.layouts.ChirpAdaptiveResultLayout
import com.alexmls.core.designsystem.theme.ChirpTheme

@Composable
@PreviewLightDark
@PreviewScreenSizes
fun ChirpAdaptiveResultLayoutPreview() {
    ChirpTheme {
        ChirpAdaptiveResultLayout(
            modifier = Modifier
                .fillMaxSize(),
            content = {
                Box (modifier = Modifier.height(336.dp)
                    .width(480.dp)) {
                    Text(
                        text = "Registration successful!",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        )
    }
}