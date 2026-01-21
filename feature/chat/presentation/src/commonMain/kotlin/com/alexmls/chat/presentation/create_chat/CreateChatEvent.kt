package com.alexmls.chat.presentation.create_chat

import com.alexmls.chat.domain.models.Chat

sealed interface CreateChatEvent {
    data class OnChatCreated(val chat: Chat): CreateChatEvent
}