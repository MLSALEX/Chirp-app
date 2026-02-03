package com.alexmls.chat.domain.participant

import com.alexmls.chat.domain.models.ChatParticipant
import com.alexmls.core.domain.util.DataError
import com.alexmls.core.domain.util.Result

interface ChatParticipantRepository {
    suspend fun fetchLocalParticipant(): Result<ChatParticipant, DataError>
}