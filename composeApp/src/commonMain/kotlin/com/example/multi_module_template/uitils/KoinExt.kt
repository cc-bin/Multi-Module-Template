package com.example.multi_module_template.uitils

import org.core.di.CoreKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(CoreKoinModules.allModules + listOf(

        ))
    }
}