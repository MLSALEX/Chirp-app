package com.alexmls.chat.domain.models

data class ChatInfo (
    val chat: Chat,
    val messages: List<MessageWithSender>
)