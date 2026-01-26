package com.alexmls.chat.data.di

import com.alexmls.chat.data.lifecycle.AppLifecycleObserver
import com.alexmls.chat.database.DatabaseFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformChatDataModule = module {
    single { DatabaseFactory() }
    singleOf(::AppLifecycleObserver)
}