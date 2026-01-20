package com.alexmls.chat.presentation.mappers

import com.alexmls.chat.domain.models.ChatParticipant
import com.alexmls.core.designsystem.components.avatar.ChatParticipantUi

fun ChatParticipant.toUi(): ChatParticipantUi {
    return ChatParticipantUi(
        id = userId,
        username = username,
        initials = initials,
        imageUrl = profilePictureUrl
    )
}