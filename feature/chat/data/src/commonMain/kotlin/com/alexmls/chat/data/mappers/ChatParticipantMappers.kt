package com.alexmls.chat.data.mappers

import com.alexmls.chat.data.dto.ChatParticipantDto
import com.alexmls.chat.domain.models.ChatParticipant


fun ChatParticipantDto.toDomain(): ChatParticipant {
    return ChatParticipant(
        userId = userId,
        username = username,
        profilePictureUrl = profilePictureUrl
    )
}