package com.example.multi_module_template

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.LocalPlatformContext
import org.core.data.model.DarkThemeConfig
import org.core.data.repository.NetworkMonitor
import org.core.ui.AppAction
import org.core.ui.AppEvent
import org.core.ui.AppViewModel
import org.core.ui.ext.EventsEffect
import org.core.ui.ext.LocalImageLoaderProvider
import org.core.ui.ext.getDefaultImageLoader
import org.core.ui.theme.MyTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun ComposeApp(
    updateScreenCapture: (isScreenCaptureAllowed: Boolean) -> Unit,
    handleRecreate: () -> Unit,
    handleThemeMode: (osValue: Int) -> Unit,
    handleAppLocale: (locale: String?) -> Unit,
    onSplashScreenRemoved: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AppViewModel = koinViewModel(),
) {

    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.isScreenCaptureAllowed) {
        updateScreenCapture(uiState.isScreenCaptureAllowed)
    }

    EventsEffect(eventFlow = viewModel.eventFlow) { event ->
        when (event) {
            is AppEvent.ShowToast -> {}
            is AppEvent.UpdateAppLocale -> handleAppLocale(event.localeName)
            is AppEvent.UpdateAppTheme -> handleThemeMode(event.osValue)
            is AppEvent.Recreate -> handleRecreate()
        }
    }

    val isOnline by NetworkMonitor.isOnline.collectAsStateWithLifecycle(false)
    LocalImageLoaderProvider(getDefaultImageLoader(LocalPlatformContext.current)) {
        MyTheme(
            darkTheme = uiState.darkTheme,
            androidTheme = uiState.isAndroidTheme,
            useDynamicColor = uiState.isDynamicColorsEnabled,
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .safeContentPadding()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("Network Monitor $isOnline")
                Button(onClick = {
                    viewModel.trySendAction(AppAction.Internal.ThemeUpdate(
                    if(uiState.darkTheme){
                        DarkThemeConfig.LIGHT
                    }else {
                        DarkThemeConfig.DARK
                    }))
                }) {
                    Text("Current Theme ${uiState.darkTheme} Change Theme!")
                }
            }
        }
    }
}