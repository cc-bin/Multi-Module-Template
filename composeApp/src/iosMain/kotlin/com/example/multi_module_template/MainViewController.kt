package com.example.multi_module_template

import androidx.compose.ui.window.ComposeUIViewController
import com.example.multi_module_template.uitils.initKoin

fun MainViewController() = ComposeUIViewController(configure = {
    initKoin()
}) {
    SharedApp(
        updateScreenCapture = {},
        handleRecreate = {},
        handleThemeMode = {},
        handleAppLocale = {},
        onSplashScreenRemoved = {},
    )
}