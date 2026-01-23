package com.alexmls.chat.data.di


import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.alexmls.chat.data.chat.KtorChatParticipantService
import com.alexmls.chat.data.chat.KtorChatService
import com.alexmls.chat.database.DatabaseFactory
import com.alexmls.chat.domain.chat.ChatParticipantService
import com.alexmls.chat.domain.chat.ChatService
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformChatDataModule: Module

val chatDataModule = module {
    includes(platformChatDataModule)

    singleOf(::KtorChatParticipantService) bind ChatParticipantService::class
    singleOf(::KtorChatService) bind ChatService::class

    single {
        get<DatabaseFactory>()
            .create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
}