package com.alexmls.chat.data.di


import com.alexmls.chat.data.chat.KtorChatParticipantService
import com.alexmls.chat.data.chat.KtorChatService
import com.alexmls.chat.domain.chat.ChatParticipantService
import com.alexmls.chat.domain.chat.ChatService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val chatDataModule = module {
    singleOf(::KtorChatParticipantService) bind ChatParticipantService::class
    singleOf(::KtorChatService) bind ChatService::class
}