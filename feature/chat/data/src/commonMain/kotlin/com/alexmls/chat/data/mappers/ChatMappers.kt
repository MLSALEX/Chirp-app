package com.alexmls.chat.data.mappers

import com.alexmls.chat.data.dto.ChatDto
import com.alexmls.chat.domain.models.Chat
import kotlin.time.Instant

fun ChatDto.toDomain(): Chat {
    return Chat(
        id = id,
        participants = participants.map { it.toDomain() },
        lastActivityAt = Instant.parse(lastActivityAt),
        lastMessage = lastMessage?.toDomain()
    )
}