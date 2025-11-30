package org.core.di

import com.russhwolf.settings.Settings
import org.core.data.repository.UserLogoutManager
import org.core.data.repository.UserPreferencesRepository
import org.core.data.repository.UserPreferencesRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val DataModule = module{
    single { Settings() }

    singleOf(::UserPreferencesRepositoryImpl) bind UserPreferencesRepository::class

    singleOf(::UserLogoutManager)
}