package com.alexmls.chat.domain.message

import com.alexmls.chat.domain.models.ChatMessage
import com.alexmls.core.domain.util.DataError
import com.alexmls.core.domain.util.Result

interface ChatMessageService {
    suspend fun fetchMessages(
        chatId: String,
        before: String? = null
    ): Result<List<ChatMessage>, DataError.Remote>
}