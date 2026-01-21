package com.alexmls.chat.data.mappers

import com.alexmls.chat.data.dto.ChatMessageDto
import com.alexmls.chat.domain.models.ChatMessage
import kotlin.time.Instant

fun ChatMessageDto.toDomain(): ChatMessage {
    return ChatMessage(
        id = id,
        chatId = chatId,
        content = content,
        createdAt = Instant.parse(createdAt),
        senderId = senderId
    )
}