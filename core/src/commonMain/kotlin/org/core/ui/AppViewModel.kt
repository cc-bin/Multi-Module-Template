/*
 * Copyright 2025 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See See https://github.com/openMF/kmp-project-template/blob/main/LICENSE
 */
package org.core.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.core.data.model.DarkThemeConfig
import org.core.data.model.LanguageConfig
import org.core.data.model.ThemeBrand
import org.core.data.repository.UserPreferencesRepository

class AppViewModel(
    private val settingsRepository: UserPreferencesRepository,
) : BaseViewModel<AppState, AppEvent, AppAction>(AppState()) {
    init {
        settingsRepository
            .observeDarkThemeConfig
            .onEach { trySendAction(AppAction.Internal.ThemeUpdate(it)) }
            .launchIn(viewModelScope)

        settingsRepository
            .observeDynamicColorPreference
            .onEach { trySendAction(AppAction.Internal.DynamicColorsUpdate(it)) }
            .launchIn(viewModelScope)

        settingsRepository
            .observeScreenCapturePreference
            .onEach { trySendAction(AppAction.Internal.ScreenCaptureUpdate(it)) }
            .launchIn(viewModelScope)

        settingsRepository
            .observeLanguage
            .map { AppEvent.UpdateAppLocale(it.localeName) }
            .onEach(::sendEvent)
            .launchIn(viewModelScope)
    }

    override fun handleAction(action: AppAction) {
        when (action) {
            is AppAction.AppSpecificLanguageUpdate -> handleAppSpecificLanguageUpdate(action)

            is AppAction.Internal.ScreenCaptureUpdate -> handleScreenCaptureUpdate(action)

            is AppAction.Internal.ThemeUpdate -> handleAppThemeUpdated(action)

            is AppAction.Internal.DynamicColorsUpdate -> handleDynamicColorsUpdate(action)

            is AppAction.Internal.CurrentUserStateChange -> handleCurrentUserStateChange()

            is AppAction.Internal.UserUnlockStateChange -> handleUserUnlockStateChange()
        }
    }

    private fun handleAppSpecificLanguageUpdate(action: AppAction.AppSpecificLanguageUpdate) {
        viewModelScope.launch {
            settingsRepository.setLanguage(action.appLanguage)
        }
    }

    private fun handleScreenCaptureUpdate(action: AppAction.Internal.ScreenCaptureUpdate) {
        mutableStateFlow.update { it.copy(isScreenCaptureAllowed = action.isScreenCaptureEnabled) }
    }

    private fun handleAppThemeUpdated(action: AppAction.Internal.ThemeUpdate) {
        mutableStateFlow.update {
            it.copy(darkTheme = action.theme == DarkThemeConfig.DARK)
        }
        sendEvent(AppEvent.UpdateAppTheme(osValue = action.theme.osValue))
    }

    private fun handleDynamicColorsUpdate(action: AppAction.Internal.DynamicColorsUpdate) {
        mutableStateFlow.update { it.copy(isDynamicColorsEnabled = action.isDynamicColorsEnabled) }
    }

    private fun handleUserUnlockStateChange() {
        recreateUiAndGarbageCollect()
    }

    private fun handleCurrentUserStateChange() {
        recreateUiAndGarbageCollect()
    }

    private fun recreateUiAndGarbageCollect() {
        sendEvent(AppEvent.Recreate)
    }
}

data class AppState(
    val darkTheme: Boolean = false,
    val isAndroidTheme: Boolean = false,
    val isDynamicColorsEnabled: Boolean = false,
    val isScreenCaptureAllowed: Boolean = false,
)

sealed interface AppEvent {
    data object Recreate : AppEvent

    data class ShowToast(val message: String) : AppEvent

    data class UpdateAppLocale(
        val localeName: String?,
    ) : AppEvent

    data class UpdateAppTheme(
        val osValue: Int,
    ) : AppEvent
}

sealed interface AppAction {
    data class AppSpecificLanguageUpdate(val appLanguage: LanguageConfig) : AppAction

    sealed class Internal : AppAction {

        data object CurrentUserStateChange : Internal()

        data class ScreenCaptureUpdate(
            val isScreenCaptureEnabled: Boolean,
        ) : Internal()

        data class ThemeUpdate(
            val theme: DarkThemeConfig,
        ) : Internal()

        data object UserUnlockStateChange : Internal()

        data class DynamicColorsUpdate(
            val isDynamicColorsEnabled: Boolean,
        ) : Internal()
    }
}
data class UserEditableSettings(
    val brand: ThemeBrand,
    val useDynamicColor: Boolean,
    val darkThemeConfig: DarkThemeConfig,
)