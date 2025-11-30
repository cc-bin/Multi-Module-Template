package org.core.di

import org.core.ui.AppViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

object CoreKoinModules {
    private val AppModule = module {

        viewModelOf(::AppViewModel)
    }

    val allModules = listOf(
        DataModule,
        AppModule
    )
}