package com.example.multi_module_template

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.LocalPlatformContext
import org.core.ui.ext.LocalImageLoaderProvider
import org.core.ui.ext.getDefaultImageLoader

@Composable
fun SharedApp(
    updateScreenCapture: (isScreenCaptureAllowed: Boolean) -> Unit,
    handleRecreate: () -> Unit,
    handleThemeMode: (osValue: Int) -> Unit,
    handleAppLocale: (locale: String?) -> Unit,
    modifier: Modifier = Modifier,
    onSplashScreenRemoved: () -> Unit,
) {
    LocalImageLoaderProvider(getDefaultImageLoader(LocalPlatformContext.current)) {
        ComposeApp(
            updateScreenCapture = updateScreenCapture,
            handleRecreate = handleRecreate,
            handleThemeMode = handleThemeMode,
            handleAppLocale = handleAppLocale,
            onSplashScreenRemoved = onSplashScreenRemoved,
            modifier = modifier,
        )
    }
}