package com.alexmls.chat.data.di


import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.alexmls.chat.data.chat.KtorChatParticipantService
import com.alexmls.chat.data.chat.KtorChatService
import com.alexmls.chat.data.chat.OfflineFirstChatRepository
import com.alexmls.chat.data.chat.WebSocketChatConnectionClient
import com.alexmls.chat.data.message.OfflineFirstMessageRepository
import com.alexmls.chat.data.network.KtorWebSocketConnector
import com.alexmls.chat.database.DatabaseFactory
import com.alexmls.chat.domain.chat.ChatConnectionClient
import com.alexmls.chat.domain.chat.ChatParticipantService
import com.alexmls.chat.domain.chat.ChatRepository
import com.alexmls.chat.domain.chat.ChatService
import com.alexmls.chat.domain.message.MessageRepository
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformChatDataModule: Module

val chatDataModule = module {
    includes(platformChatDataModule)

    singleOf(::KtorChatParticipantService) bind ChatParticipantService::class
    singleOf(::KtorChatService) bind ChatService::class
    singleOf(::OfflineFirstChatRepository) bind ChatRepository::class
    singleOf(::OfflineFirstMessageRepository) bind MessageRepository::class
    singleOf(::WebSocketChatConnectionClient) bind ChatConnectionClient::class
    singleOf(::KtorWebSocketConnector)
    single {
        Json {
            ignoreUnknownKeys = true
        }
    }
    single {
        get<DatabaseFactory>()
            .create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
}