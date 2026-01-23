package com.alexmls.chat.data.di

import com.alexmls.chat.database.DatabaseFactory
import org.koin.dsl.module

actual val platformChatDataModule = module {
    single { DatabaseFactory() }
}