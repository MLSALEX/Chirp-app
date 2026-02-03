package com.alexmls.chat.domain.participant

import com.alexmls.chat.domain.models.ChatParticipant
import com.alexmls.core.domain.util.DataError
import com.alexmls.core.domain.util.Result

interface ChatParticipantService {
    suspend fun searchParticipant(
        query: String
    ): Result<ChatParticipant, DataError.Remote>

    suspend fun getLocalParticipant(): Result<ChatParticipant, DataError.Remote>
}