package com.example.multi_module_template

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.multi_module_template.uitils.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "multi_module_template",
    ) {
        SharedApp(
            updateScreenCapture = {},
            handleRecreate = {},
            handleThemeMode = {},
            handleAppLocale = {},
            onSplashScreenRemoved = {}
        )
    }
}