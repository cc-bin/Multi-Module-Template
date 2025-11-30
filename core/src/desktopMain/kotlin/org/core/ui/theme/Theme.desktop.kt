package org.core.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
actual fun platformColorScheme(
    useDarkTheme: Boolean,
    dynamicColor: Boolean,
): ColorScheme {
    return when (useDarkTheme) {
        true -> darkColorScheme()
        false -> lightColorScheme()
    }
}
