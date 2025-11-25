package com.example.multi_module_template

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "multi_module_template",
    ) {
        App()
    }
}